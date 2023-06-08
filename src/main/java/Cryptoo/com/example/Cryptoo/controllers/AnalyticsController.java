package Cryptoo.com.example.Cryptoo.controllers;

import Cryptoo.com.example.Cryptoo.entities.AnalyticsEntity;
import Cryptoo.com.example.Cryptoo.entities.ServiceEntity;
import Cryptoo.com.example.Cryptoo.repositories.AnalyticsRepository;
import Cryptoo.com.example.Cryptoo.repositories.CompanyRepository;
import Cryptoo.com.example.Cryptoo.repositories.ServicesRepository;
import Cryptoo.com.example.Cryptoo.responses.AnalyticsResponse;
import Cryptoo.com.example.Cryptoo.services.impl.LogsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    AnalyticsRepository analyticsRepository;

    @Autowired
    ServicesRepository servicesRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    LogsService logsService;

    @GetMapping("/{companyId}")
    public ResponseEntity<AnalyticsResponse> getAnalytics(@PathVariable String companyId){
        AnalyticsResponse response=new AnalyticsResponse();
        AnalyticsEntity entity=analyticsRepository.findByCompanyId(companyId);
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.map(entity,response);
        List<ServiceEntity> entities=servicesRepository.findAllByCompanyId(companyId);
        int impresion=0;
        int clicks=0;
        int orders=0;
        for(ServiceEntity serviceEntity:entities){
            impresion += serviceEntity.getImpression();
            clicks += serviceEntity.getNb_click();
        }
        response.setServicesImpression(impresion);
        response.setServicesClicks(clicks);
        logsService.addToLogs(companyRepository.findByCompanyId(companyId).getFullName()+" is checking the dashboard.");
        return new ResponseEntity<AnalyticsResponse>(response, HttpStatus.OK);
    }

}
