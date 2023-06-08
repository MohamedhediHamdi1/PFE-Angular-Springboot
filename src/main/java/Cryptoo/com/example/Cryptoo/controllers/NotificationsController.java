package Cryptoo.com.example.Cryptoo.controllers;

import Cryptoo.com.example.Cryptoo.entities.NotificationsEntity;
import Cryptoo.com.example.Cryptoo.repositories.NotificationsRepository;
import Cryptoo.com.example.Cryptoo.responses.NotificationsResponse;
import Cryptoo.com.example.Cryptoo.services.impl.LogsService;
import Cryptoo.com.example.Cryptoo.services.impl.ReviewServices;
import Cryptoo.com.example.Cryptoo.shared.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notifications")
public class NotificationsController {

    @Autowired
    NotificationsRepository notificationsRepository;
    @Autowired
    Utils util;
    @Autowired
    ReviewServices reviewServices;
    @Autowired
    LogsService logsService;

    @GetMapping("{userId}/{page}")
    public ResponseEntity<Map<String, Object>> getNotifications(@PathVariable String userId, @PathVariable int page){
        List<NotificationsResponse> responses=new ArrayList<>();
        Page<NotificationsEntity> pages=notificationsRepository.findAllByUser(PageRequest.of(page-1,5),userId);
        for(NotificationsEntity entity:pages){
            ModelMapper modelMapper=new ModelMapper();
            NotificationsResponse response=new NotificationsResponse();
            modelMapper.map(entity,response);
            response.setDate(reviewServices.time_Diff(entity.getDate()));
            responses.add(response);
        }
        Map<String, Object> responses1 = new HashMap<>();
        responses1.put("notifications", responses);
        responses1.put("totalItems", pages.getTotalElements());
        logsService.addToLogs("User "+userId+" checked the notifications");
        return new ResponseEntity<>(responses1, HttpStatus.OK);
    }
    @GetMapping("click/{notifId}")
    public ResponseEntity<String> vueNotifications(@PathVariable String notifId){
        NotificationsEntity entity= notificationsRepository.findByNotificationId(notifId);
        if(entity==null)
            throw new RuntimeException();
        entity.setClick(true);
        notificationsRepository.save(entity);
     return new ResponseEntity<String>("done", HttpStatus.OK);
    }

}
