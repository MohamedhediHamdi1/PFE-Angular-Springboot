package Cryptoo.com.example.Cryptoo.controllers;

import Cryptoo.com.example.Cryptoo.entities.*;
import Cryptoo.com.example.Cryptoo.repositories.*;
import Cryptoo.com.example.Cryptoo.requests.MessageRequest;
import Cryptoo.com.example.Cryptoo.requests.SimpleRequest;
import Cryptoo.com.example.Cryptoo.responses.MessageResponse;
import Cryptoo.com.example.Cryptoo.services.UserService;
import Cryptoo.com.example.Cryptoo.services.impl.LogsService;
import Cryptoo.com.example.Cryptoo.services.impl.ReviewServices;
import Cryptoo.com.example.Cryptoo.shared.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/messages")
public class MessagesController {
    @Autowired
    ChannelMessageRepository channelMessageRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    Utils util;

    @Autowired
    ReviewServices time_Diff;

    @Autowired
    ReportRepository reportRepository;
    @Autowired
    UserService userService;
    @Autowired
    LogsService logsService;



    @PostMapping("/{page}")
    public List<MessageResponse> sendMessage(@RequestBody MessageRequest request,@PathVariable int page){
        ChannelMessageEntity channelEntity=channelMessageRepository.findSenderIdAndRecieverId(request.getSenderId(), request.getRecieverId());
        if(channelEntity==null){
            ChannelMessageEntity newchannelEntity=new ChannelMessageEntity();
            newchannelEntity.setChannelId(util.generateStringId(32));
            newchannelEntity.setRecieverId(request.getRecieverId());
            newchannelEntity.setSenderId(request.getSenderId());
            if(userRepository.findByUserId(request.getSenderId()) != null){
                UserEntity user =userRepository.findByUserId(request.getSenderId());
                newchannelEntity.setSenderImage(user.getImageId());
                newchannelEntity.setSenderName(user.getFirstname()+" "+user.getLastname());
            }else if(companyRepository.findByCompanyId(request.getSenderId()) != null){
                CompanyEntity company =companyRepository.findByCompanyId(request.getSenderId());
                newchannelEntity.setSenderImage(company.getLogo());
                newchannelEntity.setSenderName(company.getDisplayName());
            }else{
                throw new RuntimeException();
            }
            if(userRepository.findByUserId(request.getRecieverId()) != null){
                UserEntity user =userRepository.findByUserId(request.getRecieverId());
                newchannelEntity.setRecieverimage(user.getImageId());
                newchannelEntity.setRecieverName(user.getFirstname()+" "+user.getLastname());
            }else if(companyRepository.findByCompanyId(request.getRecieverId()) != null){
                CompanyEntity company =companyRepository.findByCompanyId(request.getRecieverId());
                newchannelEntity.setRecieverimage(company.getLogo());
                newchannelEntity.setRecieverName(company.getDisplayName());
            }else{
                throw new RuntimeException();
            }
            newchannelEntity.setLastMessageDate(null);
            newchannelEntity.setVue(false);
            channelMessageRepository.save(newchannelEntity);
            channelEntity=newchannelEntity;
        }
        List<MessageResponse> response=new ArrayList<>();
        MessageEntity newEntity=new MessageEntity();
        BeanUtils.copyProperties(request,newEntity);
        newEntity.setMessageId(util.generateStringId(32));
        newEntity.setChannelID(channelEntity.getChannelId());
        Date newTime=new Date();
        newEntity.setSendDate(newTime);
        messageRepository.save(newEntity);
        channelEntity.setLastMessage(request.getMessage());
        channelEntity.setLastMessageDate(newTime);
        channelMessageRepository.save(channelEntity);
        Pageable pageable= PageRequest.of(page-1,10, Sort.by("sendDate").descending());
        Page<MessageEntity> messages=messageRepository.findAllByChannelID(channelEntity.getChannelId(),pageable);
        for(MessageEntity message:messages){
            MessageResponse response1=new MessageResponse();
            BeanUtils.copyProperties(message,response1);
            response.add(response1);
        }
        Collections.reverse(response);
        logsService.addToLogs(channelEntity.getSenderName()+"  sent a message to "+channelEntity.getRecieverName()+".");
        return response;
    }

    @GetMapping("/{channelId}/{page}")
    public List<MessageResponse> getMessages(@PathVariable String channelId,@PathVariable int page){
        Pageable pageable= PageRequest.of(page-1,10, Sort.by("sendDate").descending());
        Page<MessageEntity> messages=messageRepository.findAllByChannelID(channelId,pageable);
        List<MessageResponse> response=new ArrayList<>();
        for(MessageEntity message:messages){
            MessageResponse response1=new MessageResponse();
            BeanUtils.copyProperties(message,response1);
            response1.setDatemessage(time_Diff.time_Diff_Messages(message.getSendDate()));
            response.add(response1);
        }
        Collections.reverse(response);
        return response;
    }
    @DeleteMapping("/{messageId}/{page}")
    public List<MessageResponse> deleteMessage(@PathVariable String messageId,@PathVariable int page){
        MessageEntity message=messageRepository.findByMessageId(messageId);
        if(message==null)
            throw  new RuntimeException();
        message.setMessage("Deleted");
        messageRepository.save(message);
        String channelId=message.getChannelID();
        Pageable pageable= PageRequest.of(page-1,10, Sort.by("sendDate").descending());
        Page<MessageEntity> messages=messageRepository.findAllByChannelID(channelId,pageable);
        List<MessageResponse> response=new ArrayList<>();
        for(MessageEntity message1:messages){
            MessageResponse response1=new MessageResponse();
            BeanUtils.copyProperties(message1,response1);
            response.add(response1);
        }
        Collections.reverse(response);
        logsService.addToLogs("The message "+messageId+" was successfully deleted.");
        return response;
    }

    @GetMapping("/report/{messageId}/{reportBy}")
    public ResponseEntity<SimpleRequest> reportMessage(@PathVariable String messageId,@PathVariable String reportBy){
        MessageEntity message= messageRepository.findByMessageId(messageId);
        if(message==null)
            throw new RuntimeException();
        ReportEntity reportEntity=new ReportEntity();
        reportEntity.setReportId(util.generateStringId(32));
        reportEntity.setReportUser(reportBy);
        ChannelMessageEntity entity= channelMessageRepository.findByChannelId(message.getChannelID());
        if(entity==null)
            throw new RuntimeException();
        if(entity.getRecieverId()==reportBy){
            reportEntity.setReportedUser(entity.getSenderId());
        }else {
            reportEntity.setReportedUser(entity.getRecieverId());
        }
        reportEntity.setMessageId(messageId);
        reportEntity.setAdmin("0");
        reportRepository.save(reportEntity);
        SimpleRequest response=new SimpleRequest();
        response.setResponse("done");
        logsService.addToLogs(reportBy+"reported the message "+messageId+" .");
        return new ResponseEntity<SimpleRequest>(response, HttpStatus.OK);
    }

    @GetMapping("/vue/{channelId}/{type}/{userId}")
    public ResponseEntity<Map<String, Object>> updateVue(@PathVariable String channelId, @PathVariable int type,@PathVariable String userId){
        ChannelMessageEntity entity= channelMessageRepository.findByChannelId(channelId);
        if(entity==null)
            throw new RuntimeException();
        if(type==0){
            entity.setVue(false);
        }else if(type==1){
            entity.setVue(true);
        }
        else{
            throw new RuntimeException();
        }
	channelMessageRepository.save(entity);
        Map<String, Object> response = new HashMap<>();
        response.put("response", "done");
        response.put("newMessages", userService.checkMessages((userId)));
        response.put("newNotifications", userService.checkNotifications((userId)));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
