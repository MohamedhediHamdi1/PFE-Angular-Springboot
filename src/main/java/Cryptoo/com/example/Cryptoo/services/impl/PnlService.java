package Cryptoo.com.example.Cryptoo.services.impl;

import Cryptoo.com.example.Cryptoo.entities.PnlEntity;
import Cryptoo.com.example.Cryptoo.repositories.PnlRepository;
import Cryptoo.com.example.Cryptoo.shared.dto.PnlDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PnlService implements  Pnl_service{

    @Autowired
    PnlRepository pnlRepository;

    @Override
    public PnlDto getPnl(String id) {
        PnlEntity pnlEntity = pnlRepository.findByPnlid(id);
        PnlDto pnlDto = new PnlDto();
        BeanUtils.copyProperties(pnlEntity, pnlDto);
        return pnlDto;
    }
    ZonedDateTime localDate1 = ZonedDateTime.now(ZoneId.of("GMT"));
    DateTimeFormatter formatter1
            = DateTimeFormatter.ofPattern("dd");
    String today= formatter1.format(localDate1);

    @Scheduled(fixedDelay = 4000)
    public void check_date(){
        ZonedDateTime localDate = ZonedDateTime.now(ZoneId.of("GMT"));
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("dd");
        String day= formatter.format(localDate);
        if(day.equals(today)==false){
            System.out.println(day+today);
            long count = pnlRepository.count();
            for(int i=1;i<count+1;i++){
               String j = String.valueOf(i);
               PnlEntity pnlEntity=pnlRepository.findByPnlid(j);
               pnlEntity.setDay1(pnlEntity.getDay2());
               pnlEntity.setDay2(pnlEntity.getDay3());
               pnlEntity.setDay3(pnlEntity.getDay4());
               pnlEntity.setDay4(pnlEntity.getDay5());
               pnlEntity.setDay5(pnlEntity.getDay6());
               pnlEntity.setDay6(pnlEntity.getDay7());
               pnlEntity.setDay7(0.0);
               pnlRepository.save(pnlEntity);
               today=day;
            }
        }
    }

}
