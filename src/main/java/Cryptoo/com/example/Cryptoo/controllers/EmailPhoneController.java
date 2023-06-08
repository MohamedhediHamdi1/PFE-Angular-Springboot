package Cryptoo.com.example.Cryptoo.controllers;

import Cryptoo.com.example.Cryptoo.entities.EmailPhoneEntity;
import Cryptoo.com.example.Cryptoo.entities.UserEntity;
import Cryptoo.com.example.Cryptoo.repositories.EmailPhoneRepository;
import Cryptoo.com.example.Cryptoo.repositories.UserRepository;
import Cryptoo.com.example.Cryptoo.responses.EmailPhoneResponse;
import Cryptoo.com.example.Cryptoo.responses.ResetPasswordResponse;
import Cryptoo.com.example.Cryptoo.services.impl.LogsService;
import Cryptoo.com.example.Cryptoo.services.impl.ResetPasswordService;
import Cryptoo.com.example.Cryptoo.services.impl.TwilioMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.Random;

@RestController
@RequestMapping("/emailphone")
public class EmailPhoneController {

    @Autowired
    EmailPhoneRepository emailPhoneRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ResetPasswordService sendemail;

  @Autowired
  TwilioMessageSender twilioMessageSender;
  @Autowired
  LogsService logsService;



    @PostMapping
    @RequestMapping("/checkemailphone")
    public ResponseEntity<ResetPasswordResponse> checkemailphone(@RequestBody EmailPhoneResponse request){
        UserEntity user = userRepository.findByEmail(request.getEmail());
        ResetPasswordResponse response=new ResetPasswordResponse();
        if(user !=null){
            response.setResponse("Email already exist.");
        }else{
            UserEntity user1 = userRepository.findByPhone(request.getPhone());
            if(user1 != null){
                response.setResponse("Phone already exist.");
            }else{
                response.setResponse("done");
            }
        }
        return new ResponseEntity<ResetPasswordResponse>(response, HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping("/getemailphone")
    public ResponseEntity<ResetPasswordResponse> getemailphone(@RequestBody EmailPhoneResponse request){
        EmailPhoneEntity entity= emailPhoneRepository.findByEmail(request.getEmail());
        ResetPasswordResponse response=new ResetPasswordResponse();
        if(entity==null){
            response.setResponse("error");
        }else{
            if(entity.getPhone().equals(request.getPhone())){
                if(entity.getNb_try()==3 && entity.getTime()==0){
                    response.setResponse("Try after 24h.");
                }else{
                    response.setResponse("done");
                    response.setToken(String.valueOf(entity.getTime()));
                }
            }else{
                response.setResponse("error");
            }
        }
        return new ResponseEntity<ResetPasswordResponse>(response,HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping("/checkcode")
    public ResponseEntity<ResetPasswordResponse> checkcode(@RequestBody EmailPhoneResponse request){
        ResetPasswordResponse response =new ResetPasswordResponse();
        EmailPhoneEntity entity= emailPhoneRepository.findByEmail(request.getEmail());
        if(entity != null){
            if(entity.getEmailCode()==request.getEmailCode() && entity.getPhoneCode()== request.getPhoneCode()){
                response.setResponse("done");
            }else{
                response.setResponse("Invalid code.");
            }
        }else{
            response.setResponse("error");
        }
        return new ResponseEntity<ResetPasswordResponse>(response,HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping("/sendcode")
    public ResponseEntity<ResetPasswordResponse> sendcode(@RequestBody EmailPhoneResponse request){
        ResetPasswordResponse response =new ResetPasswordResponse();
        EmailPhoneEntity entity=emailPhoneRepository.findByEmail(request.getEmail());
        EmailPhoneEntity entity1=emailPhoneRepository.findByPhone(request.getPhone());
        if(entity==null  && entity1==null){
            EmailPhoneEntity newentity=new EmailPhoneEntity();
            Random random = new Random();
            int phoneCode = random.nextInt(900000) + 100000;
            Random random1 = new Random();
            int emailCode = random1.nextInt(900000) + 100000;
            newentity.setId(emailPhoneRepository.count()+1);
            newentity.setPhone(request.getPhone());
            newentity.setEmail(request.getEmail());
            newentity.setEmailCode(emailCode);
            newentity.setPhoneCode(phoneCode);
            newentity.setNb_try(1);
            try {
                sendemail.sendVerificationCode(request.getEmail(), String.valueOf(emailCode));
                twilioMessageSender.sendMessage(request.getPhone(),"+15794002954","verfication code : "+phoneCode);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            //send codes
            newentity.setTime(300);
            emailPhoneRepository.save(newentity);
            response.setResponse("done");
            logsService.addToLogs("The verification code was successfully sent to the "+request.getEmail()+"and "+request.getPhone()+" .");
        }else if(entity==null && entity1!=null  || entity!=null && entity1==null){
            if(entity!=null){
                emailPhoneRepository.delete(entity);
            }else if (entity1!=null){
                emailPhoneRepository.delete(entity1);
            }
            EmailPhoneEntity newentity=new EmailPhoneEntity();
            Random random = new Random();
            int phoneCode = random.nextInt(900000) + 100000;
            Random random1 = new Random();
            int emailCode = random1.nextInt(900000) + 100000;
            newentity.setId(emailPhoneRepository.count()+1);
            newentity.setPhone(request.getPhone());
            newentity.setEmail(request.getEmail());
            newentity.setEmailCode(emailCode);
            newentity.setPhoneCode(phoneCode);
            newentity.setNb_try(0);
            try {
                sendemail.sendVerificationCode(request.getEmail(), String.valueOf(emailCode));
                twilioMessageSender.sendMessage(request.getPhone(),"+15794002954","verfication code : "+phoneCode);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            newentity.setTime(300);
            emailPhoneRepository.save(newentity);
            response.setResponse("done");
            logsService.addToLogs("The verification code was successfully sent to the "+request.getEmail()+"and "+request.getPhone()+" .");
        }else if(entity!=null  && entity1!=null){
            if(entity.getEmail().equals(entity1.getEmail()) && entity.getPhone().equals(entity1.getPhone())){
                if(entity.getTime()<1 && entity.getNb_try()>2){
                    response.setResponse("Try After 24h.");
                }else if(entity.getTime()>1){
                    response.setResponse("Code Already sent.");
                }else if(entity.getTime()<1 && entity.getNb_try()<3){
                    Random random = new Random();
                    int phoneCode = random.nextInt(900000) + 100000;
                    Random random1 = new Random();
                    int emailCode = random1.nextInt(900000) + 100000;
                    entity.setPhone(request.getPhone());
                    entity.setEmail(request.getEmail());
                    entity.setEmailCode(emailCode);
                    entity.setPhoneCode(phoneCode);
                    entity.setNb_try(entity.getNb_try()+1);
                    try {
                        sendemail.sendVerificationCode(request.getEmail(), String.valueOf(emailCode));
                        twilioMessageSender.sendMessage(request.getPhone(),"+15794002954","verfication code : "+phoneCode);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                    entity.setTime(300);
                    emailPhoneRepository.save(entity);
                    response.setResponse("done");
                    logsService.addToLogs("The verification code was successfully again sent to the "+request.getEmail()+"and "+request.getPhone()+" .");
                }else{
                    response.setResponse("error");
                }
            }
            else{
                emailPhoneRepository.delete(entity);
                emailPhoneRepository.delete(entity1);
                EmailPhoneEntity newentity=new EmailPhoneEntity();
                Random random = new Random();
                int phoneCode = random.nextInt(900000) + 100000;
                Random random1 = new Random();
                int emailCode = random1.nextInt(900000) + 100000;
                newentity.setId(emailPhoneRepository.count()+1);
                newentity.setPhone(request.getPhone());
                newentity.setEmail(request.getEmail());
                newentity.setEmailCode(emailCode);
                newentity.setPhoneCode(phoneCode);
                newentity.setNb_try(0);
                try {
                    sendemail.sendVerificationCode(request.getEmail(), String.valueOf(emailCode));
                    twilioMessageSender.sendMessage(request.getPhone(),"+15794002954","verfication code : "+phoneCode);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
                newentity.setTime(300);
                emailPhoneRepository.save(newentity);
                response.setResponse("done");
                logsService.addToLogs("The verification code was successfully sent to the "+request.getEmail()+"and "+request.getPhone()+" .");
            }
        }else{
            response.setResponse("error");
        }
        System.out.println(response.getResponse());
        return new ResponseEntity<ResetPasswordResponse>(response,HttpStatus.OK);
    }






}
