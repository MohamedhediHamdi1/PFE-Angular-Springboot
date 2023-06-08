package Cryptoo.com.example.Cryptoo.controllers;

import Cryptoo.com.example.Cryptoo.entities.ChannelMessageEntity;
import Cryptoo.com.example.Cryptoo.repositories.ChannelMessageRepository;
import Cryptoo.com.example.Cryptoo.repositories.MessageRepository;
import Cryptoo.com.example.Cryptoo.responses.ChannelMessageResponse;
import Cryptoo.com.example.Cryptoo.services.UserService;
import Cryptoo.com.example.Cryptoo.services.impl.LogsService;
import Cryptoo.com.example.Cryptoo.services.impl.ReviewServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/channelmessage")
public class ChannelMessageController {

    @Autowired
    ChannelMessageRepository channelMessageRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    ReviewServices time_Diff;
    @Autowired
    UserService userService;
    @Autowired
    LogsService logsService;

    @GetMapping("/{recieverId}/{page}/{search}")
    public ResponseEntity<Map<String, Object>> getChannels(@PathVariable String recieverId, @PathVariable int page, @PathVariable String search){
        if(search.equals("*"))
            search="";
        List<ChannelMessageResponse> responseList=new ArrayList<>();
        Pageable pageable= PageRequest.of(page-1,10);
        Page<ChannelMessageEntity> pages=channelMessageRepository.findAllChannelsearch(pageable,recieverId,search);
        for(ChannelMessageEntity entity:pages){
            ChannelMessageResponse response=new ChannelMessageResponse();
            BeanUtils.copyProperties(entity,response);
            response.setLastMessageDate(time_Diff.time_Diff_Messages(entity.getLastMessageDate()));
            responseList.add(response);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("channels", responseList);
        response.put("newMessages", userService.checkMessages((recieverId)));
        response.put("newNotifications", userService.checkNotifications((recieverId)));
        response.put("totalItems",pages.getTotalElements());
        logsService.addToLogs(recieverId+" is checking their messages.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{channelId}/{recieverId}/{page}")
    public List<ChannelMessageResponse> deleteChannel(@PathVariable String channelId,@PathVariable String recieverId,@PathVariable int page){
        ChannelMessageEntity deleteEntity= channelMessageRepository.findByChannelId(channelId);
        channelMessageRepository.delete(deleteEntity);
        List<ChannelMessageResponse> responseList=new ArrayList<>();
        Pageable pageable= PageRequest.of(page-1,10);
        Page<ChannelMessageEntity> pages=channelMessageRepository.findAllChannel(pageable,recieverId);
        for(ChannelMessageEntity entity:pages){
            ChannelMessageResponse response=new ChannelMessageResponse();
            BeanUtils.copyProperties(entity,response);
            response.setLastMessageDate(time_Diff.time_Diff_Messages(entity.getLastMessageDate()));
            responseList.add(response);
        }
        logsService.addToLogs(recieverId+" deleted their messages "+channelId+".");
        return responseList;
    }

}
