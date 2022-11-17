package Cryptoo.com.example.Cryptoo.services.impl;

import Cryptoo.com.example.Cryptoo.entities.ContactEntity;
import Cryptoo.com.example.Cryptoo.entities.PriceEntity;
import Cryptoo.com.example.Cryptoo.repositories.ContactRepository;
import Cryptoo.com.example.Cryptoo.repositories.PriceRepository;
import Cryptoo.com.example.Cryptoo.services.ContactService;
import Cryptoo.com.example.Cryptoo.shared.dto.ContactDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceimpl implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    PriceRepository priceRepository;



    @Override
    public ContactDto getContact(String contactId) {
        ContactEntity contactEntity = contactRepository.findByContactId(contactId);
        ContactDto contactDto = new ContactDto();
        BeanUtils.copyProperties(contactEntity, contactDto);
        return contactDto;
    }


    @Override
    public ContactDto updateWallet(String contactId, ContactDto contactDto) {
        ContactEntity contactEntity = contactRepository.findByContactId(contactId);
        if(contactDto.getWallet() != null){
            contactEntity.setWallet(contactDto.getWallet());
        }
        if(contactDto.getEntryPrice1() != null){
            contactEntity.setEntryPrice1(contactDto.getEntryPrice1());
        }
        if(contactDto.getEntryPrice2() != null){
            contactEntity.setEntryPrice2(contactDto.getEntryPrice2());
        }
        if(contactDto.getEntryPrice3() != null){
            contactEntity.setEntryPrice3(contactDto.getEntryPrice3());
        }
        if(contactDto.getTakeProfit1() != null){
            contactEntity.setTakeProfit1(contactDto.getTakeProfit1());
        }
        if(contactDto.getTakeProfit2() != null){
            contactEntity.setTakeProfit2(contactDto.getTakeProfit2());
        }
        if(contactDto.getTakeProfit3() != null){
            contactEntity.setTakeProfit3(contactDto.getTakeProfit3());
        }
        if(contactDto.getStopLoss1() != null){
            contactEntity.setStopLoss1(contactDto.getStopLoss1());
        }
        if(contactDto.getStopLoss2() != null){
            contactEntity.setStopLoss2(contactDto.getStopLoss2());
        }
        if(contactDto.getStopLoss3() != null){
            contactEntity.setStopLoss3(contactDto.getStopLoss3());
        }
        if(contactDto.getOrder1() != null){
            contactEntity.setOrder1(contactDto.getOrder1());
        }
        if(contactDto.getOrder2() != null){
            contactEntity.setOrder2(contactDto.getOrder2());
        }
        if(contactDto.getOrder3() != null){
            contactEntity.setOrder3(contactDto.getOrder3());
        }
        if(contactDto.getOrder1() != null){
            contactEntity.setOrder1(contactDto.getOrder1());
        }
        if(contactDto.getPosition1() != null){
            contactEntity.setPosition1(contactDto.getPosition1());
        }
        if(contactDto.getPosition2() != null){
            contactEntity.setPosition2(contactDto.getPosition2());
        }
        if(contactDto.getPosition3() != null){
            contactEntity.setPosition3(contactDto.getPosition3());
        }
        if(contactDto.getPair1() != null){
            contactEntity.setPair1(contactDto.getPair1());
        }
        if(contactDto.getPair2() != null){
            contactEntity.setPair2(contactDto.getPair2());
        }
        if(contactDto.getPair3() != null){
            contactEntity.setPair3(contactDto.getPair3());
        }
        if(contactDto.getLeverage1() != null){
            contactEntity.setLeverage1(contactDto.getLeverage1());
        }
        if(contactDto.getLeverage2() != null){
            contactEntity.setLeverage2(contactDto.getLeverage2());
        }
        if(contactDto.getLeverage3() != null){
            contactEntity.setLeverage3(contactDto.getLeverage3());
        }
        if(contactDto.getLongorshort1() != null){
            contactEntity.setLongorshort1(contactDto.getLongorshort1());
        }
        if(contactDto.getLongorshort2() != null){
            contactEntity.setLongorshort2(contactDto.getLongorshort2());
        }
        if(contactDto.getLongorshort3() != null){
            contactEntity.setLongorshort3(contactDto.getLongorshort3());
        }
        if(contactDto.getQuantity1() != null){
            contactEntity.setQuantity1(contactDto.getQuantity1());
        }
        if(contactDto.getQuantity2() != null){
            contactEntity.setQuantity2(contactDto.getQuantity2());
        }
        if(contactDto.getQuantity3() != null){
            contactEntity.setQuantity3(contactDto.getQuantity3());
        }
        if(contactDto.getLiqprice1() != null){
            contactEntity.setLiqprice1(contactDto.getLiqprice1());
        }
        if(contactDto.getLiqprice2() != null){
            contactEntity.setLiqprice2(contactDto.getLiqprice2());
        }
        if(contactDto.getLiqprice3() != null){
            contactEntity.setLiqprice3(contactDto.getLiqprice3());
        }



        ContactEntity updated = contactRepository.save(contactEntity);
        ContactDto contactDto1 = new ContactDto();
        BeanUtils.copyProperties(updated,contactDto1);
        return contactDto1;
    }


    @Scheduled(fixedRate = 5000)
    public void manageOrder(){


/*******************************************----MANAGEORDER----***************************************************************/

    long count = contactRepository.count();


    for (int i=1;i<count+1;i++){
        ContactEntity contactEntity = contactRepository.findByIdd(i*2);
        /* **********************************************--Order1--************************************************ */
        if (contactEntity.getOrder1()==true){
            double entryPrice1=Double.parseDouble(contactEntity.getEntryPrice1());
            double takeProfit1=Double.parseDouble(contactEntity.getTakeProfit1());
            double stopLoss1=Double.parseDouble(contactEntity.getStopLoss1());
            PriceEntity priceEntity = priceRepository.findByName(contactEntity.getPair1());
            double price1 = Double.parseDouble(priceEntity.getPrice());
            if (contactEntity.getLongorshort1()==true ){
                if (entryPrice1 >= price1 && entryPrice1>0){
                    contactEntity.setPosition1(true);
                    contactEntity.setOrder1(false);
                    System.out.println("Trade opened");
                    contactRepository.save(contactEntity);
                }
            }
            else if (contactEntity.getLongorshort1()==false ){
                if (entryPrice1 <= price1 && entryPrice1>0){
                    contactEntity.setPosition1(true);
                    contactEntity.setOrder1(false);
                    System.out.println("Trade opened");
                    contactRepository.save(contactEntity);
                }
            }
        }
        /* **********************************************--Order2--************************************************ */
        if (contactEntity.getOrder2()==true){
            double entryPrice2=Double.parseDouble(contactEntity.getEntryPrice2());
            double takeProfit2=Double.parseDouble(contactEntity.getTakeProfit2());
            double stopLoss2=Double.parseDouble(contactEntity.getStopLoss2());
            PriceEntity priceEntity = priceRepository.findByName(contactEntity.getPair2());
            double price2 = Double.parseDouble(priceEntity.getPrice());
            if (contactEntity.getLongorshort2()==true ){
                if (entryPrice2 >= price2 && entryPrice2>0){
                    contactEntity.setPosition2(true);
                    contactEntity.setOrder2(false);
                    System.out.println("Trade opened");
                    contactRepository.save(contactEntity);
                }
            }
            else if (contactEntity.getLongorshort3()==false ){
                if (entryPrice2 <= price2 && entryPrice2>0){
                    contactEntity.setPosition2(true);
                    contactEntity.setOrder2(false);
                    System.out.println("Trade opened");
                    contactRepository.save(contactEntity);
                }
            }
        }
       /* **********************************************--Order3--************************************************ */
        if (contactEntity.getOrder3()==true){
            double entryPrice3=Double.parseDouble(contactEntity.getEntryPrice3());
            double takeProfit3=Double.parseDouble(contactEntity.getTakeProfit3());
            double stopLoss3=Double.parseDouble(contactEntity.getStopLoss3());
            PriceEntity priceEntity = priceRepository.findByName(contactEntity.getPair3());
            double price3 = Double.parseDouble(priceEntity.getPrice());
            if (contactEntity.getLongorshort3()==true ){
                if (entryPrice3 >= price3 && entryPrice3>0){
                    contactEntity.setPosition3(true);
                    contactEntity.setOrder3(false);
                    System.out.println("Trade opened");
                    contactRepository.save(contactEntity);
                }
            }
            else if (contactEntity.getLongorshort3()==false ){
                if (entryPrice3 <= price3 && entryPrice3>0){
                    contactEntity.setPosition3(true);
                    contactEntity.setOrder3(false);
                    System.out.println("Trade opened");
                    contactRepository.save(contactEntity);
                }
            }
        }
        /*******************************************----MANAGEPOSITION----***************************************************************/



        /* **********************************************--POSITION1--************************************************ */
        if(contactEntity.getPosition1()==true){
            double entryPrice1=Double.parseDouble(contactEntity.getEntryPrice1());
            double takeProfit1=Double.parseDouble(contactEntity.getTakeProfit1());
            double stopLoss1=Double.parseDouble(contactEntity.getStopLoss1());
            PriceEntity priceEntity = priceRepository.findByName(contactEntity.getPair1());
            double price1 = Double.parseDouble(priceEntity.getPrice());
            if (contactEntity.getLongorshort1()==true ){
                if((price1<=stopLoss1 &&  stopLoss1>0) || (price1>=takeProfit1 && takeProfit1>0 )){
                    double close_price1=price1;
                    double pnl = 0;
                    double quantity1=contactEntity.getQuantity1()*0.96;  //Maker fee
                    pnl = ((close_price1-entryPrice1)/entryPrice1)*quantity1*contactEntity.getLeverage1();
                    contactEntity.setWallet(contactEntity.getWallet()+pnl);
                    contactEntity.setEntryPrice1("0");
                    contactEntity.setTakeProfit1("0");
                    contactEntity.setStopLoss1("0");
                    contactEntity.setPosition1(false);
                    contactRepository.save(contactEntity);
                }
            }
            if (contactEntity.getLongorshort1()==false){
                if((price1>=stopLoss1  && stopLoss1>0) || (price1<=takeProfit1 && takeProfit1>0)){
                    double close_price1=price1;
                    double pnl = 0;
                    double quantity1=contactEntity.getQuantity1()*0.96;
                    pnl = ((entryPrice1-close_price1)/entryPrice1)*quantity1*contactEntity.getLeverage1();
                    contactEntity.setWallet(contactEntity.getWallet()+pnl);
                    contactEntity.setEntryPrice1("0");
                    contactEntity.setTakeProfit1("0");
                    contactEntity.setStopLoss1("0");
                    contactEntity.setPosition1(false);
                    contactRepository.save(contactEntity);
                }
            }

        }
        /* **********************************************--Position2--************************************************ */

        if(contactEntity.getPosition2()==true){
            double entryPrice2=Double.parseDouble(contactEntity.getEntryPrice2());
            double takeProfit2=Double.parseDouble(contactEntity.getTakeProfit2());
            double stopLoss2=Double.parseDouble(contactEntity.getStopLoss2());
            PriceEntity priceEntity = priceRepository.findByName(contactEntity.getPair2());
            double price2 = Double.parseDouble(priceEntity.getPrice());
            if (contactEntity.getLongorshort2()==true ){
                if((price2<=stopLoss2 &&  stopLoss2>0) || (price2>=takeProfit2 && takeProfit2>0 )){
                    double close_price2=price2;
                    double pnl = 0;
                    double quantity2=contactEntity.getQuantity2()*0.96;  //Maker fee
                    pnl = ((close_price2-entryPrice2)/entryPrice2)*quantity2*contactEntity.getLeverage2();
                    contactEntity.setWallet(contactEntity.getWallet()+pnl);
                    contactEntity.setEntryPrice2("0");
                    contactEntity.setTakeProfit2("0");
                    contactEntity.setStopLoss2("0");
                    contactEntity.setPosition2(false);
                    contactRepository.save(contactEntity);
                }
            }
            if (contactEntity.getLongorshort2()==false){
                if((price2>=stopLoss2  && stopLoss2>0) || (price2<=takeProfit2 && takeProfit2>0)){
                    double close_price2=price2;
                    double pnl = 0;
                    double quantity2=contactEntity.getQuantity2()*0.96;
                    pnl = ((entryPrice2-close_price2)/entryPrice2)*quantity2*contactEntity.getLeverage2();
                    contactEntity.setWallet(contactEntity.getWallet()+pnl);
                    contactEntity.setEntryPrice2("0");
                    contactEntity.setTakeProfit2("0");
                    contactEntity.setStopLoss2("0");
                    contactEntity.setPosition2(false);
                    contactRepository.save(contactEntity);
                }
            }

        }

        /* **********************************************--Position3--************************************************ */

        if(contactEntity.getPosition3()==true){
            double entryPrice3=Double.parseDouble(contactEntity.getEntryPrice3());
            double takeProfit3=Double.parseDouble(contactEntity.getTakeProfit3());
            double stopLoss3=Double.parseDouble(contactEntity.getStopLoss3());
            PriceEntity priceEntity = priceRepository.findByName(contactEntity.getPair3());
            double price3 = Double.parseDouble(priceEntity.getPrice());
            if (contactEntity.getLongorshort3()==true ){
                if((price3<=stopLoss3 &&  stopLoss3>0) || (price3>=takeProfit3 && takeProfit3>0 )){
                    double close_price3=price3;
                    double pnl = 0;
                    double quantity3=contactEntity.getQuantity3()*0.96;  //Maker fee
                    pnl = ((close_price3-entryPrice3)/entryPrice3)*quantity3*contactEntity.getLeverage3();
                    contactEntity.setWallet(contactEntity.getWallet()+pnl);
                    contactEntity.setEntryPrice3("0");
                    contactEntity.setTakeProfit3("0");
                    contactEntity.setStopLoss3("0");
                    contactEntity.setPosition3(false);
                    contactRepository.save(contactEntity);
                }
            }
            if (contactEntity.getLongorshort3()==false){
                if((price3>=stopLoss3  && stopLoss3>0) || (price3<=takeProfit3 && takeProfit3>0)){
                    double close_price3=price3;
                    double pnl = 0;
                    double quantity3=contactEntity.getQuantity3()*0.96;
                    pnl = ((entryPrice3-close_price3)/entryPrice3)*quantity3*contactEntity.getLeverage3();
                    contactEntity.setWallet(contactEntity.getWallet()+pnl);
                    contactEntity.setEntryPrice3("0");
                    contactEntity.setTakeProfit3("0");
                    contactEntity.setStopLoss3("0");
                    contactEntity.setPosition3(false);
                    contactRepository.save(contactEntity);
                }
            }

        }

        }
}



}
