package Cryptoo.com.example.Cryptoo.controllers;

import Cryptoo.com.example.Cryptoo.entities.ServiceEntity;
import Cryptoo.com.example.Cryptoo.repositories.CompanyRepository;
import Cryptoo.com.example.Cryptoo.repositories.ServicesRepository;
import Cryptoo.com.example.Cryptoo.requests.ServiceRequest;
import Cryptoo.com.example.Cryptoo.requests.SimpleRequest;
import Cryptoo.com.example.Cryptoo.responses.ResetPasswordResponse;
import Cryptoo.com.example.Cryptoo.responses.ServiceResponse;
import Cryptoo.com.example.Cryptoo.responses.ServiceResponse2;
import Cryptoo.com.example.Cryptoo.services.impl.LogsService;
import Cryptoo.com.example.Cryptoo.shared.Utils;
import org.modelmapper.ModelMapper;
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
@RequestMapping("/services")
public class ServicesController {

    @Autowired
    ServicesRepository servicesRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    Utils util;
    @Autowired
    LogsService logsService;

    @PostMapping
    public ResponseEntity<ResetPasswordResponse> createService(@RequestBody ServiceRequest request){
        List<ServiceEntity> entities=servicesRepository.findAllByCompanyId(request.getCompanyId());
        if(entities.size()>4)
            throw  new RuntimeException();
        ResetPasswordResponse response=new ResetPasswordResponse();
        ServiceEntity newEntity =new ServiceEntity();
        newEntity.setId(servicesRepository.count()+1);
        newEntity.setPrivateId(util.generateStringId(32));
        newEntity.setName(request.getName());
        newEntity.setCompanyId(request.getCompanyId());
        newEntity.setDescription(request.getDescription());
        newEntity.setMore_description(request.getMore_description());
        newEntity.setCategory(request.getCategory());
        newEntity.setDate(request.getDate());
        newEntity.setImage(request.getImage());
        newEntity.setVideo(request.getVideo());
        newEntity.setPrice(request.getPrice());
        newEntity.setReview(request.getReview());
        newEntity.setVisible(true);
        newEntity.setNb_buy(0);
        newEntity.setNb_click(0);
        newEntity.setImpression(0);
        newEntity.setVerified(true);
        servicesRepository.save(newEntity);
        response.setResponse("done");
        logsService.addToLogs("Company "+request.getCompanyId()+" created a new service called Service "+request.getName()+".");
        return new ResponseEntity<ResetPasswordResponse>(response, HttpStatus.CREATED);
    }


    @GetMapping("search/{search}/{page}")
    public ResponseEntity<Map<String, Object>> getSearchServices(@PathVariable int page, @RequestParam(value = "limit",defaultValue = "8") int limit, @PathVariable String search) {
        if(search.equals("*")){search="";}
        List<ServiceResponse> serviceResponse=new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page-1, limit);
        Page<ServiceEntity> servicePage;
        servicePage = servicesRepository.findAllServices_search(pageableRequest,search);
        List<ServiceEntity> services = servicePage.getContent();
        for(ServiceEntity serviceEntity: services) {
            if(serviceEntity.isVerified() && serviceEntity.isVisible()){
                ModelMapper modelMapper = new ModelMapper();
                ServiceResponse service1 = modelMapper.map(serviceEntity,ServiceResponse.class);
                service1.setLogoCompany(companyRepository.findByCompanyId(service1.getCompanyId()).getLogo());
                service1.setNameCompany(companyRepository.findByCompanyId(service1.getCompanyId()).getFullName());
                serviceResponse.add(service1);
            }
        }
        Map<String, Object> response = new HashMap<>();
        response.put("services", serviceResponse);
        response.put("totalItems", servicePage.getTotalElements());
        if(search.equals("")||search.equals(" ")||search==null){
            logsService.addToLogs("The user checked all services on page "+page);
        }else{
            logsService.addToLogs("The user is searching for "+search+" on page "+page);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceResponse2> getService(@PathVariable String serviceId){
        ServiceEntity serviceEntity=servicesRepository.findByPrivateId(serviceId);
        ModelMapper modelMapper=new ModelMapper();
        if(!serviceEntity.isVerified() || !serviceEntity.isVisible())
            throw  new RuntimeException();;
        ServiceResponse2 response=modelMapper.map(serviceEntity,ServiceResponse2.class);
        response.setLogoCompany(companyRepository.findByCompanyId(serviceEntity.getCompanyId()).getLogo());
        response.setNameCompany(companyRepository.findByCompanyId(serviceEntity.getCompanyId()).getFullName());
        logsService.addToLogs("The user checked Service "+serviceId+" .");
        return new ResponseEntity<ServiceResponse2>(response,HttpStatus.OK);
    }



    @GetMapping("recomandation/{search}/{page}")
    public ResponseEntity<Map<String, Object>> getRecomandationServices(@PathVariable int page, @RequestParam(value = "limit",defaultValue = "4") int limit, @PathVariable String search) {
        List<ServiceResponse> serviceResponse=new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page-1, limit);
        Page<ServiceEntity> servicePage;
        servicePage = servicesRepository.findAllRecomandation_search(pageableRequest,search);
        List<ServiceEntity> services = servicePage.getContent();
        for(ServiceEntity serviceEntity: services) {

            ModelMapper modelMapper = new ModelMapper();
            ServiceResponse service1 = modelMapper.map(serviceEntity,ServiceResponse.class);
            serviceResponse.add(service1);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("services", serviceResponse);
        response.put("totalItems", servicePage.getTotalElements());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("companyservice/{companyId}")
    public ResponseEntity<List<ServiceResponse>> companyServices(@PathVariable String companyId){
        List<ServiceResponse> serviceResponse=new ArrayList<>();
        List<ServiceEntity> entity=servicesRepository.findAllByCompanyId(companyId);
        if(entity.size()==0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        for(ServiceEntity serviceEntity: entity) {
            if(serviceEntity.isVerified() ) {
                ModelMapper modelMapper = new ModelMapper();
                ServiceResponse service1 = modelMapper.map(serviceEntity, ServiceResponse.class);
                serviceResponse.add(service1);
            }
        }
        return new ResponseEntity<List<ServiceResponse>>(serviceResponse,HttpStatus.OK);
    }

    @GetMapping("/hideunhide/{serviceId}")
    public ResponseEntity<SimpleRequest> hideUnhide(@PathVariable String serviceId){
        ServiceEntity entity=servicesRepository.findByPrivateId(serviceId);
        if(entity==null)
            throw new RuntimeException();
        entity.setVisible(!entity.isVisible());
        servicesRepository.save(entity);
        SimpleRequest response =new SimpleRequest();
        response.setResponse("done");
        if(entity.isVisible()){
            logsService.addToLogs("Service "+serviceId+" has been unhidden by the company.");
        }else{
            logsService.addToLogs("Service "+serviceId+" has been hidden by the company.");
        }
        return new ResponseEntity<SimpleRequest>(response,HttpStatus.OK);
    }
    @GetMapping("/delete/{serviceId}")
    public ResponseEntity<SimpleRequest> delete(@PathVariable String serviceId){
        ServiceEntity entity=servicesRepository.findByPrivateId(serviceId);
        if(entity==null)
            throw new RuntimeException();
        servicesRepository.delete(entity);
        SimpleRequest response =new SimpleRequest();
        response.setResponse("done");
        logsService.addToLogs("Service "+serviceId+" has been deleted by the company.");
        return new ResponseEntity<SimpleRequest>(response,HttpStatus.OK);
    }
    @PostMapping("/edit/{serviceId}")
    public ResponseEntity<SimpleRequest> editService(@PathVariable String serviceId,@RequestBody SimpleRequest request){
        ServiceEntity entity=servicesRepository.findByPrivateId(serviceId);
        if(entity==null)
            throw new RuntimeException();
        if(request.getRequest().equals("name")){
            entity.setName(request.getResponse());
        }else if(request.getRequest().equals("description")){
            entity.setDescription(request.getResponse());
        }else if(request.getRequest().equals("more_description")){
            entity.setMore_description(request.getResponse());
        }
        else if(request.getRequest().equals("category")){
            entity.setCategory(request.getResponse());
        }else if(request.getRequest().equals("price")){
            entity.setPrice(Double.valueOf(request.getResponse()));
        }else if(request.getRequest().equals("image")){
            entity.setImage(request.getResponse());
        }else if(request.getRequest().equals("video")){
            entity.setVideo(request.getResponse());
        }
        else{
            throw new RuntimeException();
        }
        servicesRepository.save(entity);
        SimpleRequest response=new SimpleRequest();
        response.setResponse("done");
        logsService.addToLogs("The "+request.getRequest()+" of Service "+serviceId+" was updated successfully.");
        return new ResponseEntity<SimpleRequest>(response,HttpStatus.OK);
    }


    }
