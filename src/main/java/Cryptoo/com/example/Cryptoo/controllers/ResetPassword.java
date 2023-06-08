package Cryptoo.com.example.Cryptoo.controllers;

import Cryptoo.com.example.Cryptoo.entities.ResetPasswordEntity;
import Cryptoo.com.example.Cryptoo.entities.UserEntity;
import Cryptoo.com.example.Cryptoo.repositories.Passwordrepository;
import Cryptoo.com.example.Cryptoo.repositories.UserRepository;
import Cryptoo.com.example.Cryptoo.requests.SimpleRequest;
import Cryptoo.com.example.Cryptoo.responses.ResetPasswordResponse;
import Cryptoo.com.example.Cryptoo.services.impl.LogsService;
import Cryptoo.com.example.Cryptoo.services.impl.ResetPasswordService;
import Cryptoo.com.example.Cryptoo.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Random;

@RestController
@RequestMapping("/ResetPassword")
public class ResetPassword  {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;

    @Autowired
    Passwordrepository passwordrepository;

    @Autowired
    ResetPasswordService resetPasswordService;

    @Autowired
    Utils util;
    @Autowired
    LogsService logsService;




    @PostMapping("/email")
    public ResponseEntity<ResetPasswordResponse> confirmEmail(@RequestBody ResetPasswordEntity resetRequest) throws MessagingException {
        System.out.println(resetRequest.getEmail());
       UserEntity user = userRepository.findByEmail(resetRequest.getEmail());
       String response="";
        HttpStatus http=HttpStatus.OK;
        ResetPasswordResponse resetPasswordResponse = new ResetPasswordResponse();
        resetPasswordResponse.setToken("0");
        if (user == null) {
            response="Invalid email address";
        }
        else if(user.getEmail().equals(resetRequest.getEmail()))  {

            ResetPasswordEntity check = passwordrepository.findByEmail(resetRequest.getEmail());
            if(check==null){
                Random random = new Random();
                int verificationCode = random.nextInt(900000) + 100000;
                ResetPasswordEntity pass = new ResetPasswordEntity();
                resetPasswordService.sendVerificationCode(resetRequest.getEmail(), String.valueOf(verificationCode));
                response="verification Code sent to "+resetRequest.getEmail();
                pass.setId(passwordrepository.count()+1);
                pass.setEmail(resetRequest.getEmail());
                pass.setTime(180);
                pass.setVerificationCode(verificationCode);
                pass.setNb_try(1);
                pass.setToken1(util.generateStringId(32));
                passwordrepository.save(pass);
                resetPasswordResponse.setToken(pass.getToken1());
            }else{
                ResetPasswordEntity pass =passwordrepository.findByEmail(resetRequest.getEmail());
                if(pass.getTime()>0){
                    response="verification Code already sent";
                }else{
                    if(pass.getNb_try()<3){
                        Random random = new Random();
                        int verificationCode = random.nextInt(900000) + 100000;
                        resetPasswordService.sendVerificationCode(pass.getEmail(), String.valueOf(verificationCode));
                        response="verification Code sent to "+resetRequest.getEmail();
                        pass.setTime(180);
                        pass.setVerificationCode(verificationCode);
                        pass.setNb_try(pass.getNb_try()+1);
                        pass.setToken1(util.generateStringId(32));
                        passwordrepository.save(pass);
                        resetPasswordResponse.setToken(pass.getToken1());
                    }else{
                        response="Try after 24h";
                    }
                }

            }
        }
        else {
            response="error";
        }

        resetPasswordResponse.setResponse(response);
        return new ResponseEntity<ResetPasswordResponse>(resetPasswordResponse, HttpStatus.OK);
    }
    @PostMapping("/code")
    public ResponseEntity<ResetPasswordResponse> confirmCode(@RequestBody ResetPasswordEntity resetRequest) throws MessagingException {
        ResetPasswordEntity pass = passwordrepository.findByEmail(resetRequest.getEmail());
        String response = "error";
        ResetPasswordResponse resetPasswordResponse = new ResetPasswordResponse();
        resetPasswordResponse.setToken("0");
        if(pass != null && resetRequest.getToken1().equals(pass.getToken1())) {

            if (pass.getVerificationCode() == resetRequest.getVerificationCode() && pass.getTime()>1 && pass.getNb_try()<4 ) {
                response = "done";
                pass.setToken2(util.generateStringId(32));
                passwordrepository.save(pass);
                resetPasswordResponse.setToken(pass.getToken2());
            }else if(pass.getVerificationCode() != resetRequest.getVerificationCode() && pass.getTime()>1){
                response="Invalid verification Code";
            }else if(pass.getNb_try()<3 && pass.getTime()<1){
                response="Verification Code expired";
            }else if(pass.getNb_try()>=3 && pass.getTime()<1){
                response="Try after 24h";
            }else{
                response="verification error";
            }
        }else{
            response="error";
        }
        System.out.println(response);
        resetPasswordResponse.setResponse(response);
        return new ResponseEntity<ResetPasswordResponse>(resetPasswordResponse, HttpStatus.OK);
    }


    @PostMapping("/newpassword")
    public ResponseEntity<ResetPasswordResponse> newpassword(@RequestBody ResetPasswordEntity resetRequest) throws MessagingException {
        ResetPasswordResponse resetPasswordResponse = new ResetPasswordResponse();
        resetPasswordResponse.setResponse("error");
        ResetPasswordEntity pass = passwordrepository.findByEmail(resetRequest.getEmail());
       if(pass != null && resetRequest.getToken2().equals(pass.getToken2())){
            UserEntity user = userRepository.findByEmail(resetRequest.getEmail());
            user.setEncryptedPassword(bCryptPasswordEncoder.encode(resetRequest.getToken1()));
            userRepository.save(user);
            resetPasswordResponse.setResponse("Password Updated!");
           pass.setToken1("");
           pass.setToken2("");
           pass.setTime(0);
           pass.setNb_try(3);
           passwordrepository.save(pass);
           logsService.addToLogs(resetRequest.getEmail()+" successfully reset their password.");
        }
        return new ResponseEntity<ResetPasswordResponse>(resetPasswordResponse,HttpStatus.OK);
    }

    @PostMapping("/oldpassword/{userId}")
    public ResponseEntity<SimpleRequest> oldPassword(@PathVariable String userId,@RequestBody SimpleRequest request){
        SimpleRequest response = new SimpleRequest();
        UserEntity userEntity=userRepository.findByUserId(userId);
        if (userEntity==null){
            response.setResponse("error");
        }else{
            if( bCryptPasswordEncoder.matches(request.getRequest(),userEntity.getEncryptedPassword())){
                userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(request.getResponse()));
                userRepository.save(userEntity);
                response.setResponse("done");
                logsService.addToLogs(userId+" successfully updated their password.");
            }else{
                response.setResponse("Invalid Password");
            }
        }
        return new ResponseEntity<SimpleRequest>(response,HttpStatus.OK);
    }
}
