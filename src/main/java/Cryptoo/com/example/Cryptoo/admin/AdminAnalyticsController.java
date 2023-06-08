package Cryptoo.com.example.Cryptoo.admin;

import Cryptoo.com.example.Cryptoo.admin.Responses.AdminAnalyticsResponse;
import Cryptoo.com.example.Cryptoo.repositories.CompanyRepository;
import Cryptoo.com.example.Cryptoo.repositories.ServicesRepository;
import Cryptoo.com.example.Cryptoo.repositories.UserRepository;
import Cryptoo.com.example.Cryptoo.services.impl.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/analytics")
public class AdminAnalyticsController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    ServicesRepository servicesRepository;
    @Autowired
    LogsService logsService;

    @GetMapping
    public ResponseEntity<AdminAnalyticsResponse> getAnalytics(){
        AdminAnalyticsResponse response=new AdminAnalyticsResponse();
        response.setUsers(String.valueOf(userRepository.count()));
        response.setCompanies(String.valueOf(companyRepository.count()));
        response.setServices(String.valueOf(servicesRepository.count()));
        logsService.addToLogs("Admin receives server analytics.");
        return new ResponseEntity<AdminAnalyticsResponse>(response, HttpStatus.OK);
    }
}
