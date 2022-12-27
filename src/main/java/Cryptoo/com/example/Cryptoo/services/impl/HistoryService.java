package Cryptoo.com.example.Cryptoo.services.impl;

import Cryptoo.com.example.Cryptoo.entities.HistoryEntity;
import Cryptoo.com.example.Cryptoo.repositories.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Service
public class HistoryService {
    @Autowired
    HistoryRepository historyRepository;
    public static HistoryEntity historyupdate(String pair,String entryprice,String closeprice,String entrytime,String closetime,String pnl,String quantity,String buy_sell,HistoryEntity historyEntity){
        //HistoryEntity historyEntity= historyRepository.findById(historyid).get();
        if(historyEntity.getEntryprice1().equals("0")){
            historyEntity.setEntryprice1(entryprice);
            historyEntity.setCloseprice1(closeprice);
            historyEntity.setEntrytime1(entrytime);
            historyEntity.setClosetime1(closetime);
            historyEntity.setPair1(pair);
            historyEntity.setBuy_sell1(buy_sell);
            historyEntity.setQuantity1(quantity);
            historyEntity.setPnl1(pnl);
        }
        else if(historyEntity.getEntryprice2().equals("0")){
            historyEntity.setEntryprice2(entryprice);
            historyEntity.setCloseprice2(closeprice);
            historyEntity.setEntrytime2(entrytime);
            historyEntity.setClosetime2(closetime);
            historyEntity.setPair2(pair);
            historyEntity.setBuy_sell2(buy_sell);
            historyEntity.setQuantity2(quantity);
            historyEntity.setPnl2(pnl);
        }
        else if(historyEntity.getEntryprice3().equals("0")){
            historyEntity.setEntryprice3(entryprice);
            historyEntity.setCloseprice3(closeprice);
            historyEntity.setEntrytime3(entrytime);
            historyEntity.setClosetime3(closetime);
            historyEntity.setPair3(pair);
            historyEntity.setBuy_sell3(buy_sell);
            historyEntity.setQuantity3(quantity);
            historyEntity.setPnl3(pnl);
        }
        else if(historyEntity.getEntryprice4().equals("0")){
            historyEntity.setEntryprice4(entryprice);
            historyEntity.setCloseprice4(closeprice);
            historyEntity.setEntrytime4(entrytime);
            historyEntity.setClosetime4(closetime);
            historyEntity.setPair4(pair);
            historyEntity.setBuy_sell4(buy_sell);
            historyEntity.setQuantity4(quantity);
            historyEntity.setPnl4(pnl);
        }
        else if(historyEntity.getEntryprice5().equals("0")){
            historyEntity.setEntryprice5(entryprice);
            historyEntity.setCloseprice5(closeprice);
            historyEntity.setEntrytime5(entrytime);
            historyEntity.setClosetime5(closetime);
            historyEntity.setPair5(pair);
            historyEntity.setBuy_sell5(buy_sell);
            historyEntity.setQuantity5(quantity);
            historyEntity.setPnl5(pnl);
        }
        else if(historyEntity.getEntryprice6().equals("0")){
            historyEntity.setEntryprice6(entryprice);
            historyEntity.setCloseprice6(closeprice);
            historyEntity.setEntrytime6(entrytime);
            historyEntity.setClosetime6(closetime);
            historyEntity.setPair6(pair);
            historyEntity.setBuy_sell6(buy_sell);
            historyEntity.setQuantity6(quantity);
            historyEntity.setPnl6(pnl);
        }
        else if(historyEntity.getEntryprice7().equals("0")){
            historyEntity.setEntryprice7(entryprice);
            historyEntity.setCloseprice7(closeprice);
            historyEntity.setEntrytime7(entrytime);
            historyEntity.setClosetime7(closetime);
            historyEntity.setPair7(pair);
            historyEntity.setBuy_sell7(buy_sell);
            historyEntity.setQuantity7(quantity);
            historyEntity.setPnl7(pnl);
        }
        else if(historyEntity.getEntryprice8().equals("0")){
            historyEntity.setEntryprice8(entryprice);
            historyEntity.setCloseprice8(closeprice);
            historyEntity.setEntrytime8(entrytime);
            historyEntity.setClosetime8(closetime);
            historyEntity.setPair8(pair);
            historyEntity.setBuy_sell8(buy_sell);
            historyEntity.setQuantity8(quantity);
            historyEntity.setPnl8(pnl);
        }
        else if(historyEntity.getEntryprice8().equals("0")==false){
            historyEntity.setEntryprice1(historyEntity.getEntryprice2());
            historyEntity.setEntryprice2(historyEntity.getEntryprice3());
            historyEntity.setEntryprice3(historyEntity.getEntryprice4());
            historyEntity.setEntryprice4(historyEntity.getEntryprice5());
            historyEntity.setEntryprice5(historyEntity.getEntryprice6());
            historyEntity.setEntryprice6(historyEntity.getEntryprice7());
            historyEntity.setEntryprice7(historyEntity.getEntryprice8());
            historyEntity.setEntryprice8(entryprice);

            historyEntity.setCloseprice1(historyEntity.getCloseprice2());
            historyEntity.setCloseprice2(historyEntity.getCloseprice3());
            historyEntity.setCloseprice3(historyEntity.getCloseprice4());
            historyEntity.setCloseprice4(historyEntity.getCloseprice5());
            historyEntity.setCloseprice5(historyEntity.getCloseprice6());
            historyEntity.setCloseprice6(historyEntity.getCloseprice7());
            historyEntity.setCloseprice7(historyEntity.getCloseprice8());
            historyEntity.setCloseprice8(closeprice);

            historyEntity.setEntrytime1(historyEntity.getEntrytime2());
            historyEntity.setEntrytime2(historyEntity.getEntrytime3());
            historyEntity.setEntrytime3(historyEntity.getEntrytime4());
            historyEntity.setEntrytime4(historyEntity.getEntrytime5());
            historyEntity.setEntrytime5(historyEntity.getEntrytime6());
            historyEntity.setEntrytime6(historyEntity.getEntrytime7());
            historyEntity.setEntrytime7(historyEntity.getEntrytime8());
            historyEntity.setEntrytime8(entrytime);

            historyEntity.setClosetime1(historyEntity.getClosetime2());
            historyEntity.setClosetime2(historyEntity.getClosetime3());
            historyEntity.setClosetime3(historyEntity.getClosetime4());
            historyEntity.setClosetime4(historyEntity.getClosetime5());
            historyEntity.setClosetime5(historyEntity.getClosetime6());
            historyEntity.setClosetime6(historyEntity.getClosetime7());
            historyEntity.setClosetime7(historyEntity.getClosetime8());
            historyEntity.setClosetime8(closetime);

            historyEntity.setPair1(historyEntity.getPair2());
            historyEntity.setPair2(historyEntity.getPair3());
            historyEntity.setPair3(historyEntity.getPair4());
            historyEntity.setPair4(historyEntity.getPair5());
            historyEntity.setPair5(historyEntity.getPair6());
            historyEntity.setPair6(historyEntity.getPair7());
            historyEntity.setPair7(historyEntity.getPair8());
            historyEntity.setPair8(pair);

            historyEntity.setPnl1(historyEntity.getPnl2());
            historyEntity.setPnl2(historyEntity.getPnl3());
            historyEntity.setPnl3(historyEntity.getPnl4());
            historyEntity.setPnl4(historyEntity.getPnl5());
            historyEntity.setPnl5(historyEntity.getPnl6());
            historyEntity.setPnl6(historyEntity.getPnl7());
            historyEntity.setPnl7(historyEntity.getPnl8());
            historyEntity.setPnl8(pnl);

            historyEntity.setQuantity1(historyEntity.getQuantity2());
            historyEntity.setQuantity2(historyEntity.getQuantity3());
            historyEntity.setQuantity3(historyEntity.getQuantity4());
            historyEntity.setQuantity4(historyEntity.getQuantity5());
            historyEntity.setQuantity5(historyEntity.getQuantity6());
            historyEntity.setQuantity6(historyEntity.getQuantity7());
            historyEntity.setQuantity7(historyEntity.getQuantity8());
            historyEntity.setQuantity8(quantity);

            historyEntity.setBuy_sell1(historyEntity.getBuy_sell2());
            historyEntity.setBuy_sell2(historyEntity.getBuy_sell3());
            historyEntity.setBuy_sell3(historyEntity.getBuy_sell4());
            historyEntity.setBuy_sell4(historyEntity.getBuy_sell5());
            historyEntity.setBuy_sell5(historyEntity.getBuy_sell6());
            historyEntity.setBuy_sell6(historyEntity.getBuy_sell7());
            historyEntity.setBuy_sell7(historyEntity.getBuy_sell8());
            historyEntity.setBuy_sell8(buy_sell);
        }
        return historyEntity;
    }
    public static String country_time(String s){
        final Date currentTime = new Date();

        final SimpleDateFormat sdf =
                new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone(s));
        //System.out.println("GMT time: " + sdf.format(currentTime));
        return sdf.format(currentTime);
    }
}
