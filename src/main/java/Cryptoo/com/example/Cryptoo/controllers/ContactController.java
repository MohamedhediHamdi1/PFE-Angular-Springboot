package Cryptoo.com.example.Cryptoo.controllers;

import Cryptoo.com.example.Cryptoo.entities.ContactEntity;
import Cryptoo.com.example.Cryptoo.repositories.ContactRepository;
import Cryptoo.com.example.Cryptoo.responses.ContactResponse;
import Cryptoo.com.example.Cryptoo.responses.ResetPasswordResponse;
import Cryptoo.com.example.Cryptoo.services.impl.LogsService;
import Cryptoo.com.example.Cryptoo.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    Utils util;
    @Autowired
    LogsService logsService;

    @PostMapping
    public ResponseEntity<ResetPasswordResponse> create(@RequestBody ContactResponse request){
        ContactEntity contact1=contactRepository.findByIpAddress(request.getIpAddress());
        ResetPasswordResponse response=new ResetPasswordResponse();
        if(contact1==null){
            ContactEntity contact = new ContactEntity();
            contact.setId(util.generateStringId(32));
            contact.setEmail(request.getEmail());
            contact.setSubject(request.getSubject());
            contact.setDetail(request.getDetail());
            contact.setIpAddress(request.getIpAddress());
            contact.setGuest(true);
            contact.setAdmin("0");
            contactRepository.save(contact);
            response.setResponse("Thank you for your message. We will get back to you as soon as possible.");
            logsService.addToLogs("Guest "+request.getEmail()+"  successfully created a request to the support team.");
        }else{
            response.setResponse("You have already submitted a message. Please wait for us to get back to you.");
            logsService.addToLogs("Guest "+request.getEmail()+"  failed creating a request to the support team.");
        }
        return new ResponseEntity<ResetPasswordResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<ResetPasswordResponse> createForusers(@RequestBody ContactResponse request){
        ResetPasswordResponse response=new ResetPasswordResponse();
            ContactEntity contact = new ContactEntity();
            contact.setId(util.generateStringId(32));
            contact.setEmail(request.getEmail());
            contact.setSubject(request.getSubject());
            contact.setDetail(request.getDetail());
            contact.setIpAddress("0");
            contact.setGuest(false);
            contact.setAdmin("0");
            contactRepository.save(contact);
            response.setResponse("Thank you for your message. We will get back to you as soon as possible.");
            logsService.addToLogs("User "+request.getEmail()+"  successfully created a request to the support team.");
        return new ResponseEntity<ResetPasswordResponse>(response, HttpStatus.OK);
    }



}
