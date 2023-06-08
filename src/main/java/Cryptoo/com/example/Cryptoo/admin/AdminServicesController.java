package Cryptoo.com.example.Cryptoo.admin;

import Cryptoo.com.example.Cryptoo.entities.ServiceEntity;
import Cryptoo.com.example.Cryptoo.repositories.CompanyRepository;
import Cryptoo.com.example.Cryptoo.repositories.ServicesRepository;
import Cryptoo.com.example.Cryptoo.repositories.UserRepository;
import Cryptoo.com.example.Cryptoo.requests.SimpleRequest;
import Cryptoo.com.example.Cryptoo.responses.ServiceResponse;
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
@RequestMapping("admin/services")
public class AdminServicesController {

    @Autowired
    ServicesRepository servicesRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    UserRepository userRepository;
    @Autowired
    LogsService logsService;


    @GetMapping("/{page}/{search}")
    public ResponseEntity<Map<String, Object>> getSearchServices(@PathVariable int page, @PathVariable String search) {
        if(search.equals("*")){search="";}
        List<ServiceResponse> serviceResponse=new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page-1, 20);
        Page<ServiceEntity> servicePage;
        servicePage = servicesRepository.findAllServices_search1(pageableRequest,search);
        List<ServiceEntity> services = servicePage.getContent();
        for(ServiceEntity serviceEntity: services) {
                ModelMapper modelMapper = new ModelMapper();
                ServiceResponse service1 = modelMapper.map(serviceEntity,ServiceResponse.class);
                serviceResponse.add(service1);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("services", serviceResponse);
        response.put("totalItems", servicePage.getTotalElements());
        if(search.equals("")) {
            logsService.addToLogs("Admin receives All Services.");
        }else{
            logsService.addToLogs("admin is searching for "+search+" in services.");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{serviceId}")
    public ResponseEntity updateServices(@PathVariable String serviceId, @RequestBody SimpleRequest request){
        ServiceEntity service= servicesRepository.findByPrivateId(serviceId);
        if(service==null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if(request.getRequest().equals("Name")){
            service.setName(request.getResponse());
        }else if(request.getRequest().equals("price")){
            service.setPrice(Double.valueOf(request.getResponse()));
        }else if(request.getRequest().equals("category")){
            service.setCategory(request.getResponse());
        }else if(request.getRequest().equals("nb_buy")){
            service.setNb_buy(Integer.parseInt(request.getResponse()));
        }else if(request.getRequest().equals("visible")){
            if(request.getResponse().equals("true") || request.getResponse().equals("True")) {
                service.setVisible(true);
            }else if(request.getResponse().equals("false") || request.getResponse().equals("False")){
                service.setVisible(false);
            }else{
                return  new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        servicesRepository.save(service);
        logsService.addToLogs("Admin updates the Service "+serviceId+" "+request.getRequest()+" to "+request.getResponse()+".");
        return new ResponseEntity(HttpStatus.OK);
    }


    @PutMapping("/{serviceId}")
    public ResponseEntity blockCompany(@PathVariable String serviceId, @RequestBody SimpleRequest request){
        ServiceEntity service= servicesRepository.findByPrivateId(serviceId);
        if (service==null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if(request.getRequest().equals("block")){
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(userRepository.findByUserId(companyRepository.findByCompanyId(service.getCompanyId()).getUserId()).getEmail());
            message.setSubject("Your Service "+service.getName()+" is currently inactive.");
            message.setText("Reason : "+request.getResponse());
            mailSender.send(message);
            service.setVisible(false);
            servicesRepository.save(service);
            logsService.addToLogs("admin blocked the Service "+serviceId+" with a reason: "+request.getResponse()+".");
        }else if(request.getRequest().equals("unblock")){
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(userRepository.findByUserId(companyRepository.findByCompanyId(service.getCompanyId()).getUserId()).getEmail());
            message.setSubject("Your Service is activated");
            message.setText("Your Service "+service.getName()+" is made active after staff verification.");
            mailSender.send(message);
            service.setVisible(true);
            servicesRepository.save(service);
            logsService.addToLogs("admin unblocked the Service "+serviceId+" .");
        }else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
