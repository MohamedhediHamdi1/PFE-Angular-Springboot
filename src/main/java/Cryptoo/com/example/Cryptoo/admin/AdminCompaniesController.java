package Cryptoo.com.example.Cryptoo.admin;

import Cryptoo.com.example.Cryptoo.admin.Responses.AdminCompanyResponse;
import Cryptoo.com.example.Cryptoo.entities.CompanyEntity;
import Cryptoo.com.example.Cryptoo.repositories.CompanyRepository;
import Cryptoo.com.example.Cryptoo.repositories.UserRepository;
import Cryptoo.com.example.Cryptoo.requests.SimpleRequest;
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
@RequestMapping("admin/company/")
public class AdminCompaniesController {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    UserRepository userRepository;
    @Autowired
    LogsService logsService;

    @GetMapping("/{page}/{search}/{type}")
    public ResponseEntity<Map<String, Object>> getAll(@PathVariable int page, @PathVariable String search,@PathVariable String type){
        List<AdminCompanyResponse> responseList=new ArrayList<>();
        if(search.equals("*"))
            search="";
        Pageable pageRequest= PageRequest.of(page-1,20);
        Page<CompanyEntity> pageList;
        if(type.equals("active")) {
            pageList = companyRepository.findAllCompaniesWithActive(pageRequest, search,true);
        }else if(type.equals("desactive")) {
            pageList = companyRepository.findAllCompaniesWithActive(pageRequest, search,false);
        }else if(type.equals("verified")) {
            pageList = companyRepository.findAllCompaniesWithVerified(pageRequest, search,true);
        }else if(type.equals("unverified")) {
            pageList = companyRepository.findAllCompaniesWithVerified(pageRequest, search,false);
        }
        else{
            type="all";
            pageList = companyRepository.findAllCompanies(pageRequest, search);
        }
        List<CompanyEntity> companyList=pageList.getContent();
        for(CompanyEntity companyEntity:companyList){
            ModelMapper modelMapper=new ModelMapper();
            AdminCompanyResponse response=new AdminCompanyResponse();
            responseList.add(modelMapper.map(companyEntity,AdminCompanyResponse.class));
        }
        Map<String, Object> response = new HashMap<>();
        response.put("list", responseList);
        response.put("totalItems", pageList.getTotalElements());
        logsService.addToLogs("Admin receives "+type+" Companies.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/{companyId}")
    public ResponseEntity updateCompany(@PathVariable String companyId, @RequestBody SimpleRequest request){
        CompanyEntity company= companyRepository.findByCompanyId(companyId);
        if(company==null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if(request.getRequest().equals("Name")){
            company.setFullName(request.getResponse());
        }else if(request.getRequest().equals("Country")){
            company.setCountry(request.getResponse());
        }else if(request.getRequest().equals("State")){
            company.setState(request.getResponse());
        }else if(request.getRequest().equals("Address")){
            company.setAdress(request.getResponse());
        }else if(request.getRequest().equals("Cor_Id")){
            company.setCorporation(request.getResponse());
        }else if(request.getRequest().equals("Business_Id")){
            company.setFullName(request.getResponse());
        }else if(request.getRequest().equals("Website")){
            company.setWebsite(request.getResponse());
        }else if(request.getRequest().equals("Category")) {
            company.setCategory(request.getResponse());
        }else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        companyRepository.save(company);
        logsService.addToLogs("Admin updates the company's "+companyId+" "+request.getRequest()+" to "+request.getResponse()+".");
        return new ResponseEntity(HttpStatus.OK);
    }




    @PutMapping("/{companyId}")
    public ResponseEntity blockCompany(@PathVariable String companyId, @RequestBody SimpleRequest request){
        CompanyEntity company= companyRepository.findByCompanyId(companyId);
        if (company==null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if(request.getRequest().equals("block")){
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(userRepository.findByUserId(company.getUserId()).getEmail());
            message.setSubject("Your company is currently inactive.");
            message.setText("Reason : "+request.getResponse());
            mailSender.send(message);
            company.setActive(false);
            companyRepository.save(company);
            logsService.addToLogs("admin blocked the company "+companyId+" with a reason: "+request.getResponse()+".");
        }else if(request.getRequest().equals("unblock")){
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(userRepository.findByUserId(company.getUserId()).getEmail());
            message.setSubject("Your company is activated");
            message.setText("Your company is made active after staff verification.");
            mailSender.send(message);
            company.setActive(true);
            companyRepository.save(company);
            logsService.addToLogs("admin unblocked the company "+companyId+" .");
        }else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }



}



