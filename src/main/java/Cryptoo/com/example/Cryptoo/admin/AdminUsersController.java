package Cryptoo.com.example.Cryptoo.admin;

import Cryptoo.com.example.Cryptoo.entities.UserEntity;
import Cryptoo.com.example.Cryptoo.repositories.UserRepository;
import Cryptoo.com.example.Cryptoo.requests.SimpleRequest;
import Cryptoo.com.example.Cryptoo.responses.UserResponse;
import Cryptoo.com.example.Cryptoo.services.impl.LogsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/users/")
public class AdminUsersController {

    @Autowired
     UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    LogsService logsService;

    @GetMapping("/{page}/{search}")
    public ResponseEntity<Map<String, Object>> getAll(@PathVariable int page, @PathVariable String search){
        List<UserResponse> responseList=new ArrayList<>();
        if(search.equals("*"))
            search="";
        Pageable pageRequest=PageRequest.of(page-1,20);
        Page pageList =userRepository.findAllUsers(pageRequest,search);
        List<UserEntity> usersList=pageList.getContent();
        for(UserEntity userEntity:usersList){
            ModelMapper modelMapper=new ModelMapper();
            responseList.add(modelMapper.map(userEntity,UserResponse.class));
        }
        Map<String, Object> response = new HashMap<>();
        response.put("list", responseList);
        response.put("totalItems", pageList.getTotalElements());
        if(search.equals("")) {
            logsService.addToLogs("Admin receives All Users.");
        }else{
            logsService.addToLogs("admin is searching for "+search+" in Users.");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity updateUser(@PathVariable String userId, @RequestBody SimpleRequest request){
        UserEntity user =userRepository.findByUserId(userId);
        if(user==null)
            return  new ResponseEntity(HttpStatus.BAD_REQUEST);
        if(request.getRequest().equals("FirstName")){
            user.setFirstname(request.getResponse());
        }else if(request.getRequest().equals("LastName")){
            user.setLastname(request.getResponse());
        }else if(request.getRequest().equals("Country")){
            user.setCountry(request.getResponse());
        }else if(request.getRequest().equals("State")){
            user.setState(request.getResponse());
        }else if(request.getRequest().equals("Address")){
            user.setAddress1(request.getResponse());
        }else if(request.getRequest().equals("MemberShip")){
            if(request.getResponse().equals("true") || request.getResponse().equals("True")) {
                user.setMembership(true);
            }else if(request.getResponse().equals("false") || request.getResponse().equals("False")){
                user.setMembership(false);
            }else{
                return  new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }else{
            return  new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        userRepository.save(user);
        logsService.addToLogs("Admin updates the User "+userId+" "+request.getRequest()+" to "+request.getResponse()+".");
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity blockCompany(@PathVariable String userId, @RequestBody SimpleRequest request){
        UserEntity user= userRepository.findByUserId(userId);
        if (user==null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if(request.getRequest().equals("block")){
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Your Account is currently inactive.");
            message.setText("Reason : "+request.getResponse());
            mailSender.send(message);
            user.setActive(false);
            userRepository.save(user);
            logsService.addToLogs("admin blocked the User "+userId+" with a reason: "+request.getResponse()+".");
        }else if(request.getRequest().equals("unblock")){
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Your Account is activated");
            message.setText("Your Account is made active after staff verification.");
            mailSender.send(message);
            user.setActive(true);
            userRepository.save(user);
            logsService.addToLogs("admin unblocked the User "+userId+" .");
        }else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }


}
