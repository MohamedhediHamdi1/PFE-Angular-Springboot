package Cryptoo.com.example.Cryptoo.services.impl;

import Cryptoo.com.example.Cryptoo.entities.AnalyticsEntity;
import Cryptoo.com.example.Cryptoo.entities.OrdersEntity;
import Cryptoo.com.example.Cryptoo.repositories.AnalyticsRepository;
import Cryptoo.com.example.Cryptoo.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AnalyticsService {
    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    AnalyticsRepository analyticsRepository;

    public void updateOrders(String companyId){
        List<OrdersEntity> orders=ordersRepository.findAllByCompany(companyId);
        int New=0;
        int Accepted=0;
        int Delivered=0;
        int Cancelled=0;
        for(OrdersEntity ordersEntity:orders){
            if(ordersEntity.getStatus().equals("New")){New +=1;}
            else if(ordersEntity.getStatus().equals("Accepted")){Accepted +=1;}
            else if(ordersEntity.getStatus().equals("Delivered")){Delivered +=1;}
            else if(ordersEntity.getStatus().equals("Cancelled")){Cancelled +=1;}
        }
        AnalyticsEntity analyticsEntity=analyticsRepository.findByCompanyId(companyId);
        analyticsEntity.setNewOrders(New);
        analyticsEntity.setAcceptedOrders(Accepted);
        analyticsEntity.setCompletedOrders(Delivered);
        analyticsEntity.setCancelledOrders(Cancelled);
        analyticsEntity.setTotalOrders(Accepted+New+Delivered+Cancelled);
        analyticsRepository.save(analyticsEntity);
    }

}
