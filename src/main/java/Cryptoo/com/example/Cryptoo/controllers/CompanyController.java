package Cryptoo.com.example.Cryptoo.controllers;

import Cryptoo.com.example.Cryptoo.entities.CompanyEntity;
import Cryptoo.com.example.Cryptoo.repositories.CompanyRepository;
import Cryptoo.com.example.Cryptoo.requests.CompanyRequest;
import Cryptoo.com.example.Cryptoo.requests.SimpleRequest;
import Cryptoo.com.example.Cryptoo.responses.CompanyResponse;
import Cryptoo.com.example.Cryptoo.services.UserService;
import Cryptoo.com.example.Cryptoo.services.impl.CompanyService;
import Cryptoo.com.example.Cryptoo.services.impl.LogsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CompanyService companyService;
    @Autowired
    UserService userService;
    @Autowired
    LogsService logsService;



    @PostMapping("/{userId}")
    public ResponseEntity<SimpleRequest> createCompany(@PathVariable String userId, @RequestBody CompanyRequest request){
        SimpleRequest response =new SimpleRequest();
        if(companyRepository.findByCorporation(request.getCorporation()) != null || companyRepository.findByBusinessNumber(request.getBusinessNumber()) != null || companyRepository.findByUserId(userId) != null){
            response.setResponse("Company Already Exist!");
            logsService.addToLogs("User "+userId+" failed to create a company.");
            return new ResponseEntity<SimpleRequest>(response, HttpStatus.OK);
        }
        boolean checkCompany=false;
        try {
            checkCompany= companyService.checkCompany(request.getCorporation(),request.getBusinessNumber());
            System.out.println(checkCompany);
        } catch (IOException e) {
            System.out.println("error conecting with FCI Canada");
        }
        if(checkCompany==true){
            companyService.createCompnay(userId,request);
            response.setResponse("done");
            logsService.addToLogs("User "+userId+" has successfully created a company.");
        }else{
            response.setResponse("false");
        }
        return new ResponseEntity<SimpleRequest>(response, HttpStatus.OK);
    }

    @GetMapping("/image/{businessNumber}/{imgaId}")
    public ResponseEntity<SimpleRequest> updateImage(@PathVariable String businessNumber,@PathVariable String imgaId){
        SimpleRequest response =new SimpleRequest();
        CompanyEntity entity =companyRepository.findByBusinessNumber(businessNumber);
        entity.setLogo(imgaId);
        companyRepository.save(entity);
        response.setResponse("done");
        logsService.addToLogs(entity.getFullName()+" updated its profile image.");
        return new ResponseEntity<SimpleRequest>(response,HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CompanyResponse> getCompany(@PathVariable String userId){
        CompanyEntity entity=companyRepository.findByUserId(userId);
        if(entity==null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        ModelMapper modelMapper=new ModelMapper();
        CompanyResponse response =modelMapper.map(entity,CompanyResponse.class);
        response.setMessage(userService.checkMessages(entity.getCompanyId()));
        response.setNotification(userService.checkNotifications(entity.getCompanyId()));
        return new ResponseEntity<CompanyResponse>(response,HttpStatus.OK);
    }

@GetMapping("/company/{userId}")
    public ResponseEntity<CompanyResponse> getCompany1(@PathVariable String userId){
        CompanyEntity entity=companyRepository.findByCompanyId(userId);
        if(entity==null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        ModelMapper modelMapper=new ModelMapper();
        CompanyResponse response =modelMapper.map(entity,CompanyResponse.class);
        response.setMessage(userService.checkMessages(entity.getCompanyId()));
        response.setNotification(userService.checkNotifications(entity.getCompanyId()));
        return new ResponseEntity<CompanyResponse>(response,HttpStatus.OK);
    }

}
