package Cryptoo.com.example.Cryptoo.controllers;

import Cryptoo.com.example.Cryptoo.entities.CompanyEntity;
import Cryptoo.com.example.Cryptoo.entities.OrdersEntity;
import Cryptoo.com.example.Cryptoo.entities.ServiceEntity;
import Cryptoo.com.example.Cryptoo.entities.UserEntity;
import Cryptoo.com.example.Cryptoo.repositories.CompanyRepository;
import Cryptoo.com.example.Cryptoo.repositories.OrdersRepository;
import Cryptoo.com.example.Cryptoo.repositories.ServicesRepository;
import Cryptoo.com.example.Cryptoo.repositories.UserRepository;
import Cryptoo.com.example.Cryptoo.requests.SimpleRequest;
import Cryptoo.com.example.Cryptoo.responses.OrdersRsponse;
import Cryptoo.com.example.Cryptoo.services.impl.AnalyticsService;
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

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    AnalyticsService analyticsService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    ServicesRepository servicesRepository;
    @Autowired
    Utils utils;
    @Autowired
    LogsService logsService;


    @GetMapping("/{companyId}/{page}/{status}")
    public ResponseEntity<Map<String, Object>> getorders(@PathVariable String companyId, @PathVariable int page, @PathVariable String status){
        List<OrdersRsponse> response=new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page-1, 10);
        if(status.equals("*"))
            status="";
        Page<OrdersEntity> orderPage =ordersRepository.findAllOrdersByCompanyId(pageableRequest,companyId,status);
        if(orderPage.getTotalElements()==0)
            orderPage=ordersRepository.findAllOrdersByuser(pageableRequest,companyId,status);
        System.out.println(orderPage.getTotalElements());
        for(OrdersEntity ordersEntity: orderPage) {
            ModelMapper modelMapper = new ModelMapper();
            OrdersRsponse service1 = modelMapper.map(ordersEntity,OrdersRsponse.class);
            response.add(service1);
        }
        Map<String, Object> response1 = new HashMap<>();
        response1.put("orders", response);
        response1.put("totalItems", orderPage.getTotalElements());
        logsService.addToLogs(companyId+" checked the orders.");
        return new ResponseEntity<>(response1, HttpStatus.OK);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrdersRsponse> getOrder(@PathVariable String orderId){
        OrdersEntity entity=ordersRepository.findByOrderId(orderId);
        ModelMapper modelMapper=new ModelMapper();
        OrdersRsponse response=modelMapper.map(entity,OrdersRsponse.class);
        return new ResponseEntity<OrdersRsponse>(response,HttpStatus.OK);
    }

    @PostMapping("/{orderId}")
    public ResponseEntity<SimpleRequest> updateStatus(@PathVariable String orderId,@RequestBody SimpleRequest request){
        SimpleRequest response=new SimpleRequest();
        OrdersEntity entity=ordersRepository.findByOrderId(orderId);
        if (entity==null){
            response.setResponse("error");
        }else{
            entity.setStatus(request.getRequest());
            Date date1=new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String date = formatter.format(date1);
            if(request.getRequest().equals("Accepted")){
                entity.setStartDate(date.toString());
            }else if(request.getRequest().equals("Delivered") || request.getRequest().equals("Cancelled")){
                entity.setEndDate(date.toString());
            }
            ordersRepository.save(entity);
            analyticsService.updateOrders(entity.getCompany());
            response.setResponse("done");
            logsService.addToLogs("The status of order "+orderId+" has been updated to "+request.getRequest()+".");
        }
         return new ResponseEntity<SimpleRequest>(response,HttpStatus.OK);
    }

    @PostMapping("/{userId}/{serviceId}")
    public ResponseEntity<SimpleRequest> createOrder(@PathVariable String userId,@PathVariable String serviceId){
        SimpleRequest response=new SimpleRequest();
        UserEntity user =userRepository.findByUserId(userId);
        ServiceEntity service=servicesRepository.findByPrivateId(serviceId);
        CompanyEntity company=companyRepository.findByCompanyId(service.getCompanyId());
        if(user==null || service==null || company==null)
            throw new RuntimeException();
        OrdersEntity ordersEntity=new OrdersEntity();
        ordersEntity.setOrderId(utils.generateStringId(32));
        ordersEntity.setCompany(service.getCompanyId());
	ordersEntity.setUser(userId);
        ordersEntity.setService(serviceId);
        Date date1=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = formatter.format(date1);
        ordersEntity.setBuyDate(date.toString());
        ordersEntity.setBuyer(user.getFirstname()+" "+user.getLastname());
        ordersEntity.setEndDate("-");
        ordersEntity.setPrice(service.getPrice().toString());
        ordersEntity.setReview(6.0);
        ordersEntity.setSeller(company.getDisplayName());
        ordersEntity.setServiceName(service.getName());
        ordersEntity.setStartDate("-");
        ordersEntity.setStatus("New");
        ordersRepository.save(ordersEntity);
        response.setResponse("done");
        logsService.addToLogs("User "+userId+" created the order"+ordersEntity.getOrderId()+" successfully.");
        return new ResponseEntity<SimpleRequest>(response,HttpStatus.OK);
    }


}
