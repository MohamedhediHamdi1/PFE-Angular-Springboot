package Cryptoo.com.example.Cryptoo.controllers;

import Cryptoo.com.example.Cryptoo.entities.CompanyEntity;
import Cryptoo.com.example.Cryptoo.entities.OrdersEntity;
import Cryptoo.com.example.Cryptoo.entities.ReviewEntity;
import Cryptoo.com.example.Cryptoo.entities.ServiceEntity;
import Cryptoo.com.example.Cryptoo.repositories.*;
import Cryptoo.com.example.Cryptoo.requests.ReviewRequest;
import Cryptoo.com.example.Cryptoo.requests.SimpleRequest;
import Cryptoo.com.example.Cryptoo.responses.ReviewResponse;
import Cryptoo.com.example.Cryptoo.services.impl.LogsService;
import Cryptoo.com.example.Cryptoo.services.impl.ReviewServices;
import Cryptoo.com.example.Cryptoo.shared.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ServicesRepository servicesRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReviewServices reviewServices;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    Utils util;
    @Autowired
    LogsService logsService;

    @PostMapping
    public ResponseEntity<SimpleRequest> createReview(@RequestBody ReviewRequest request){
        if(reviewRepository.findByOrderId(request.getServiceId())!=null)
            throw new RuntimeException();
        OrdersEntity ordersEntity=ordersRepository.findByOrderId(request.getServiceId());
        if(ordersEntity==null || !ordersEntity.getUser().equals(request.getUserId()))
            throw new RuntimeException();
        ReviewEntity newEntity=new ReviewEntity();
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.map(request,newEntity);
        newEntity.setReviewid(util.generateStringId(32));
        newEntity.setServiceId(ordersEntity.getService());
        newEntity.setOrderId(request.getServiceId());
        newEntity.setUserName(userRepository.findByUserId(request.getUserId()).getFirstname()+" "+userRepository.findByUserId(request.getUserId()).getLastname());
        newEntity.setCompanyId("test");
        Date date= new Date();
        newEntity.setDate(date);
        reviewRepository.save(newEntity);
        SimpleRequest response=new SimpleRequest();
        ordersEntity.setReview((double) request.getRate());
        ordersRepository.save(ordersEntity);
        response.setResponse("done");
        logsService.addToLogs("User "+request.getUserId()+" added a new review to Service "+request.getServiceId()+" .");

        ServiceEntity serviceEntity=servicesRepository.findByPrivateId(ordersEntity.getService());
        serviceEntity.setReview(reviewServices.calculRate(ordersEntity.getService()));
        servicesRepository.save(serviceEntity);

        return new ResponseEntity<SimpleRequest>(response, HttpStatus.OK);
    }

    @GetMapping("/{type}/{serviceId}/{page}/{orderby}")
    public ResponseEntity<Map<String, Object>> getReview(@PathVariable String type,@PathVariable String serviceId, @PathVariable int page,@PathVariable boolean orderby){
        List<ReviewResponse> list=new ArrayList<>();
        Pageable pageableRequest= PageRequest.of(page-1,5);
        Page<ReviewEntity> reviewPage;
        if(type.equals("service")){
            if(orderby==true){
                reviewPage=reviewRepository.findByServiceIdPageByRate(pageableRequest,serviceId);
            }else{
                reviewPage=reviewRepository.findByServiceIdPageByDate(pageableRequest,serviceId);
            }
        }else if(type.equals("company")){
            if(orderby==true){
                reviewPage=reviewRepository.findByCompanyPageByRate(pageableRequest,serviceId);
            }else{
                reviewPage=reviewRepository.findByCompanyPageByDate(pageableRequest,serviceId);
            }
        }
        else{
            return ResponseEntity.badRequest().build();
        }

        List<ReviewEntity> reviews=reviewPage.getContent();
        for(ReviewEntity reviewEntity: reviews) {
            ModelMapper modelMapper=new ModelMapper();
            ReviewResponse reviewResponse=modelMapper.map(reviewEntity,ReviewResponse.class);
            reviewResponse.setDate(reviewServices.time_Diff(reviewEntity.getDate()));
            list.add(reviewResponse);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("services", list);
        response.put("totalItems", reviewPage.getTotalElements());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/notes/{type}/{serviceId}")
    public ResponseEntity<Map<String, Object>> getReviewNotes(@PathVariable String type,@PathVariable String serviceId){
        int stars0=0;
        int stars1=0;
        int stars2=0;
        int stars3=0;
        int stars4=0;
        int stars5=0;
        double rates=0;
        List<ReviewEntity> entities=new ArrayList<>();
        if(type.equals("service")){
            entities = reviewRepository.findAllByServiceId(serviceId);
            ServiceEntity serviceEntity=servicesRepository.findByPrivateId(serviceId);
            rates=serviceEntity.getReview();
        }else if(type.equals("company")){
            entities = reviewRepository.findAllByCompanyId(serviceId);
            CompanyEntity companyEntity=companyRepository.findByCompanyId(serviceId);
            rates=companyEntity.getReview();
        }else{
            return ResponseEntity.badRequest().build();
        }

        for (ReviewEntity entity : entities) {
            int rate = entity.getRate();
            switch (rate) {
                case 0:
                    stars0++;
                    break;
                case 1:
                    stars1++;
                    break;
                case 2:
                    stars2++;
                    break;
                case 3:
                    stars3++;
                    break;
                case 4:
                    stars4++;
                    break;
                case 5:
                    stars5++;
                    break;
            }
        }
        Map<String, Object> response = new HashMap<>();
        response.put("stars0", stars0);
        response.put("stars1", stars1);
        response.put("stars2", stars2);
        response.put("stars3", stars3);
        response.put("stars4", stars4);
        response.put("stars5", stars5);
        response.put("rate", rates);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
