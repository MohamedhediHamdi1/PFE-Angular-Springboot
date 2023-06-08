package Cryptoo.com.example.Cryptoo.controllers;

import Cryptoo.com.example.Cryptoo.entities.NotificationEntity;
import Cryptoo.com.example.Cryptoo.repositories.NotificationRepository;
import Cryptoo.com.example.Cryptoo.requests.SimpleRequest;
import Cryptoo.com.example.Cryptoo.responses.NotificationResponse;
import Cryptoo.com.example.Cryptoo.services.impl.LogsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    LogsService logsService;

    @GetMapping("/{userId}")
    public ResponseEntity<NotificationResponse> getNotification(@PathVariable String userId){
        NotificationEntity entity=notificationRepository.findByUserId(userId);
        ModelMapper modelMapper=new ModelMapper();
        NotificationResponse response=modelMapper.map(entity,NotificationResponse.class);
        return new ResponseEntity<NotificationResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<SimpleRequest> updateNotification(@PathVariable String userId,@RequestBody NotificationResponse request){
        NotificationEntity entity=notificationRepository.findByUserId(userId);
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.map(request,entity);
        notificationRepository.save(entity);
        SimpleRequest response =new SimpleRequest();
        response.setResponse("done");
        logsService.addToLogs("User "+userId+" successfully updated the notification settings.");
        return new ResponseEntity<SimpleRequest>(response,HttpStatus.OK);

    }

}
