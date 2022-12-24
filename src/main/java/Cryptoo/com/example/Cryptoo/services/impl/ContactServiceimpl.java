package Cryptoo.com.example.Cryptoo.services.impl;

import org.springframework.scheduling.annotation.Scheduled;
import Cryptoo.com.example.Cryptoo.BtcPrice;
import Cryptoo.com.example.Cryptoo.controllers.ConectedUser;
import Cryptoo.com.example.Cryptoo.entities.ContactEntity;
import Cryptoo.com.example.Cryptoo.entities.PnlEntity;
import Cryptoo.com.example.Cryptoo.repositories.ContactRepository;
import Cryptoo.com.example.Cryptoo.repositories.PnlRepository;
import Cryptoo.com.example.Cryptoo.services.ContactService;
import Cryptoo.com.example.Cryptoo.shared.dto.ContactDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceimpl implements ContactService {

    String pricebtc ;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    PnlRepository pnlRepository;

    @Override
    public ContactDto getContact(String contactId) {
        ContactEntity contactEntity = contactRepository.findByContactId(contactId);
        ContactDto contactDto = new ContactDto();
        BeanUtils.copyProperties(contactEntity, contactDto);
        return contactDto;
    }

    @Override
    public ContactDto getContact1(Long id) {
        ContactEntity contactEntity = contactRepository.findByIdd(id);
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
            System.out.println("updated");
        }
        if(contactDto.getPosition2() != null){
            contactEntity.setPosition2(contactDto.getPosition2());
            System.out.println("updated");
        }
        if(contactDto.getPosition3() != null){
            contactEntity.setPosition3(contactDto.getPosition3());
            System.out.println("updated");
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
        if(contactDto.getTypeoftrade1() != null){
            contactEntity.setTypeoftrade1(contactDto.getTypeoftrade1());
        }
        if(contactDto.getTypeoftrade2() != null){
            contactEntity.setTypeoftrade2(contactDto.getTypeoftrade2());
        }
        if(contactDto.getTypeoftrade3() != null){
            contactEntity.setTypeoftrade3(contactDto.getTypeoftrade3());
        }
        if(contactDto.getOrder1time() != null){
            contactEntity.setOrder1time(contactDto.getOrder1time());
        }
        if(contactDto.getOrder2time() != null){
            contactEntity.setOrder2time(contactDto.getOrder2time());
        }
        if(contactDto.getOrder3time() != null){
            contactEntity.setOrder3time(contactDto.getOrder3time());
        }
        if(contactDto.getOrder4() != null){
            contactEntity.setOrder4(contactDto.getOrder4());
        }
        if(contactDto.getPosition4() != null){
            contactEntity.setPosition4(contactDto.getPosition4());
        }
        if(contactDto.getEntryPrice4() != null){
            contactEntity.setEntryPrice4(contactDto.getEntryPrice4());
        }
        if(contactDto.getTakeProfit4() != null){
            contactEntity.setTakeProfit4(contactDto.getTakeProfit4());
        }
        if(contactDto.getStopLoss4() != null){
            contactEntity.setStopLoss4(contactDto.getStopLoss4());
        }
        if(contactDto.getPair4() != null){
            contactEntity.setPair4(contactDto.getPair4());
        }
        if(contactDto.getLeverage4() != null){
            contactEntity.setLeverage4(contactDto.getLeverage4());
        }
        if(contactDto.getLongorshort4() != null){
            contactEntity.setLongorshort4(contactDto.getLongorshort4());
        }
        if(contactDto.getQuantity4() != null){
            contactEntity.setQuantity4(contactDto.getQuantity4());
        }
        if(contactDto.getLiqprice4() != null){
            contactEntity.setLiqprice4(contactDto.getLiqprice4());
        }
        if(contactDto.getOrder4time() != null){
            contactEntity.setOrder4time(contactDto.getOrder4time());
        }
        if(contactDto.getTypeoftrade4() != null){
            contactEntity.setTypeoftrade4(contactDto.getTypeoftrade4());
        }
        if(contactDto.getNombreoftrade() ==1 ){
            contactEntity.setNombreoftrade(contactEntity.getNombreoftrade()+1);
        }



        ContactEntity updated = contactRepository.save(contactEntity);
        ContactDto contactDto1 = new ContactDto();
        BeanUtils.copyProperties(updated,contactDto1);
        return contactDto1;
    }


    double liquidation_price(boolean longorshort,boolean typeoftrade,double entryprice,int max,double leverage,double wallet,double quantity){
        if(typeoftrade==true){
            double m = 0 ;
            double x=0;
            if(max==125){m=0.4;}
            else if (max==100){m=0.5;}
            else if (max==50){m=1;}
            else if (max==25){m=3;}
            x=(max/leverage)*(m+m/max*(max-leverage));
            double l = 0;
            if(longorshort==true){
                l=entryprice-entryprice*x/100;
            }
            else if(longorshort==false){
                l=entryprice+entryprice*x/100;
            }
            return l;
        }
        else{
            double m = 0 ;
            double x=0;
            if(max==125){m=0.4;}
            else if (max==100){m=0.5;}
            else if (max==50){m=1;}
            else if (max==25){m=3;}
            x=(max/leverage)*(m+m/max*(max-leverage));
            double l = 0;
            double h = wallet/(quantity);
            if(longorshort==true){
                l=entryprice-entryprice*(x/100+100/leverage/100*h);
            }
            else if(longorshort==false){
                l=entryprice+entryprice*(x/100+100/leverage/100*h);
            }
            return l;
        }
    }

    double findPrice(String pair){
        if(pair.equals("btc")){return Double.parseDouble(BtcPrice.btc);}
        if(pair.equals("eth")){return Double.parseDouble(BtcPrice.eth);}
        if(pair.equals("bnb")){return Double.parseDouble(BtcPrice.bnb);}
        if(pair.equals("xrp")){return Double.parseDouble(BtcPrice.xrp);}
        if(pair.equals("doge")){return Double.parseDouble(BtcPrice.doge);}
        if(pair.equals("ada")){return Double.parseDouble(BtcPrice.ada);}
        if(pair.equals("1000shib")){return Double.parseDouble(BtcPrice.shib);}
        else {return 0.0;}
    }

    int findMax(String pair){
        if(pair.equals("btc")){return 125;}
        if(pair.equals("eth")){return 100;}
        if(pair.equals("bnb")){return 50;}
        if(pair.equals("xrp")){return 50;}
        if(pair.equals("doge")){return 25;}
        if(pair.equals("ada")){return 50;}
        if(pair.equals("1000shib")){return 25;}
        else {return 0;}
    }


    @Scheduled(fixedDelay = 2000)
    public void manageOrder(){
        System.out.println("running : "+ ConectedUser.counter);
/*******************************************----MANAGEORDER----***************************************************************/
    long count = contactRepository.count();

    for (int i=1;i<count+1;i++){
        ContactEntity contactEntity = contactRepository.findByIdd(i);
        double liqprice1 = 0;
        double liqprice2 = 0;
        double liqprice3 = 0;
        double liqprice4 = 0;

        double wallet=contactEntity.getWallet();
        if(wallet<0){contactEntity.setWallet(0.0);contactRepository.save(contactEntity);}
        if(contactEntity.getOrder1()==true || contactEntity.getPosition1()==true) {
            liqprice1 = liquidation_price(contactEntity.getLongorshort1(), contactEntity.getTypeoftrade1(), Double.parseDouble(contactEntity.getEntryPrice1()), findMax(contactEntity.getPair1()), contactEntity.getLeverage1(), wallet, contactEntity.getQuantity1());
            contactEntity.setLiqprice1(liqprice1);
            contactRepository.save(contactEntity);
            }
        if(contactEntity.getOrder2()==true || contactEntity.getPosition2()==true) {
            liqprice2 = liquidation_price(contactEntity.getLongorshort2(), contactEntity.getTypeoftrade2(), Double.parseDouble(contactEntity.getEntryPrice2()), findMax(contactEntity.getPair2()), contactEntity.getLeverage2(),wallet, contactEntity.getQuantity2());
            contactEntity.setLiqprice2(liqprice2);
            contactRepository.save(contactEntity);
        }
        if(contactEntity.getOrder3()==true || contactEntity.getPosition3()==true) {
            liqprice3 = liquidation_price(contactEntity.getLongorshort3(), contactEntity.getTypeoftrade3(), Double.parseDouble(contactEntity.getEntryPrice3()), findMax(contactEntity.getPair3()), contactEntity.getLeverage3(), wallet, contactEntity.getQuantity3());
            contactEntity.setLiqprice3(liqprice3);
            contactRepository.save(contactEntity);
        }
        if(contactEntity.getOrder4()==true || contactEntity.getPosition4()==true) {
            liqprice4 = liquidation_price(contactEntity.getLongorshort4(), contactEntity.getTypeoftrade4(), Double.parseDouble(contactEntity.getEntryPrice4()), findMax(contactEntity.getPair4()), contactEntity.getLeverage4(), wallet, contactEntity.getQuantity4());
            contactEntity.setLiqprice4(liqprice4);
            contactRepository.save(contactEntity);
        }
        /* **********************************************--Order1--************************************************ */
        if (contactEntity.getOrder1()==true ){
            double entryPrice1=Double.parseDouble(contactEntity.getEntryPrice1());
            double price1 = findPrice(contactEntity.getPair1());
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
            double price2 = findPrice(contactEntity.getPair2());
            if (contactEntity.getLongorshort2()==true ){
                if (entryPrice2 >= price2 && entryPrice2>0){
                    contactEntity.setPosition2(true);
                    contactEntity.setOrder2(false);
                    System.out.println("Trade opened");
                    contactRepository.save(contactEntity);
                }
            }
            else if (contactEntity.getLongorshort2()==false ){
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
            double price3 = findPrice(contactEntity.getPair3());
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
        /* **********************************************--Order4--************************************************ */
        if (contactEntity.getOrder4()==true){
            double entryPrice4=Double.parseDouble(contactEntity.getEntryPrice4());
            double price4 = findPrice(contactEntity.getPair4());
            if (contactEntity.getLongorshort4()==true ){
                if (entryPrice4 >= price4 && entryPrice4>0){
                    contactEntity.setPosition4(true);
                    contactEntity.setOrder4(false);
                    System.out.println("Trade opened");
                    contactRepository.save(contactEntity);
                }
            }
            else if (contactEntity.getLongorshort4()==false ){
                if (entryPrice4 <= price4 && entryPrice4>0){
                    contactEntity.setPosition4(true);
                    contactEntity.setOrder4(false);
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
            double price1 = findPrice(contactEntity.getPair1());
            if (contactEntity.getLongorshort1()==true ){
                if((price1<=stopLoss1 &&  stopLoss1>0) || (price1>=takeProfit1 && takeProfit1>0 ) || (price1<=liqprice1 && liqprice1>0)){
                    double close_price1=price1;
                    double pnl = 0;
                    double quantity1=contactEntity.getQuantity1();  //Maker fee
                    pnl = ((close_price1-entryPrice1)/entryPrice1)*quantity1*contactEntity.getLeverage1();
                    contactEntity.setWallet(wallet+pnl+quantity1);
                    PnlEntity pnlEntity=pnlRepository.findByPnlid(String.valueOf(i));
                    pnlEntity.setDay7(pnlEntity.getDay7()+pnl);
                    pnlRepository.save(pnlEntity);
                    contactEntity.setEntryPrice1("0");
                    contactEntity.setTakeProfit1("0");
                    contactEntity.setStopLoss1("0");
                    contactEntity.setPosition1(false);
                    contactEntity.setLiqprice1(0.0);
                    contactEntity.setQuantity1(0.0);
                    contactEntity.setNombreoftrade(contactEntity.getNombreoftrade()+1);
                    contactRepository.save(contactEntity);
                }
            }
            if (contactEntity.getLongorshort1()==false){
                if((price1>=stopLoss1  && stopLoss1>0) || (price1<=takeProfit1 && takeProfit1>0) || (price1>=liqprice1 && liqprice1>0)){
                    double close_price1=price1;
                    double pnl = 0;
                    double quantity1=contactEntity.getQuantity1();
                    pnl = ((entryPrice1-close_price1)/entryPrice1)*quantity1*contactEntity.getLeverage1();
                    contactEntity.setWallet(wallet+pnl+quantity1);
                    PnlEntity pnlEntity=pnlRepository.findByPnlid(String.valueOf(i));
                    pnlEntity.setDay7(pnlEntity.getDay7()+pnl);
                    pnlRepository.save(pnlEntity);
                    contactEntity.setEntryPrice1("0");
                    contactEntity.setTakeProfit1("0");
                    contactEntity.setStopLoss1("0");
                    contactEntity.setPosition1(false);
                    contactEntity.setLiqprice1(0.0);
                    contactEntity.setQuantity1(0.0);
                    contactEntity.setNombreoftrade(contactEntity.getNombreoftrade()+1);
                    contactRepository.save(contactEntity);
                }
            }

        }
        /* **********************************************--Position2--************************************************ */

        if(contactEntity.getPosition2()==true){
            double entryPrice2=Double.parseDouble(contactEntity.getEntryPrice2());
            double takeProfit2=Double.parseDouble(contactEntity.getTakeProfit2());
            double stopLoss2=Double.parseDouble(contactEntity.getStopLoss2());
            double price2 = findPrice(contactEntity.getPair2());
            if (contactEntity.getLongorshort2()==true ){
                if((price2<=stopLoss2 &&  stopLoss2>0) || (price2>=takeProfit2 && takeProfit2>0 )|| (price2<=liqprice2 && liqprice2>0)){
                    double close_price2=price2;
                    double pnl = 0;
                    double quantity2=contactEntity.getQuantity2();  //Maker fee
                    pnl = ((close_price2-entryPrice2)/entryPrice2)*quantity2*contactEntity.getLeverage2();
                    contactEntity.setWallet(wallet+pnl+quantity2);
                    contactEntity.setEntryPrice2("0");
                    contactEntity.setTakeProfit2("0");
                    contactEntity.setStopLoss2("0");
                    contactEntity.setPosition2(false);
                    contactEntity.setLiqprice2(0.0);
                    contactEntity.setQuantity2(0.0);
                    contactEntity.setNombreoftrade(contactEntity.getNombreoftrade()+1);
                    contactRepository.save(contactEntity);
                }
            }
            if (contactEntity.getLongorshort2()==false){
                if((price2>=stopLoss2  && stopLoss2>0) || (price2<=takeProfit2 && takeProfit2>0) || (price2>=liqprice2 && liqprice2>0) ){
                    double close_price2=price2;
                    double pnl = 0;
                    double quantity2=contactEntity.getQuantity2();
                    pnl = ((entryPrice2-close_price2)/entryPrice2)*quantity2*contactEntity.getLeverage2();
                    contactEntity.setWallet(wallet+pnl+quantity2);
                    contactEntity.setEntryPrice2("0");
                    contactEntity.setTakeProfit2("0");
                    contactEntity.setStopLoss2("0");
                    contactEntity.setPosition2(false);
                    contactEntity.setLiqprice2(0.0);
                    contactEntity.setQuantity2(0.0);
                    contactEntity.setNombreoftrade(contactEntity.getNombreoftrade()+1);
                    contactRepository.save(contactEntity);
                }
            }

        }

        /* **********************************************--Position3--************************************************ */

        if(contactEntity.getPosition3()==true){
            double entryPrice3=Double.parseDouble(contactEntity.getEntryPrice3());
            double takeProfit3=Double.parseDouble(contactEntity.getTakeProfit3());
            double stopLoss3=Double.parseDouble(contactEntity.getStopLoss3());
            double price3 = findPrice(contactEntity.getPair3());
            if (contactEntity.getLongorshort3()==true ){
                if((price3<=stopLoss3 &&  stopLoss3>0) || (price3>=takeProfit3 && takeProfit3>0 ) || (price3<=liqprice3 && liqprice3>0) ){
                    double close_price3=price3;
                    double pnl = 0;
                    double quantity3=contactEntity.getQuantity3();  //Maker fee
                    pnl = ((close_price3-entryPrice3)/entryPrice3)*quantity3*contactEntity.getLeverage3();
                    contactEntity.setWallet(wallet+pnl+quantity3);
                    contactEntity.setEntryPrice3("0");
                    contactEntity.setTakeProfit3("0");
                    contactEntity.setStopLoss3("0");
                    contactEntity.setPosition3(false);
                    contactEntity.setLiqprice3(0.0);
                    contactEntity.setQuantity3(0.0);
                    contactEntity.setNombreoftrade(contactEntity.getNombreoftrade()+1);
                    contactRepository.save(contactEntity);
                }
            }
            if (contactEntity.getLongorshort3()==false){
                if((price3>=stopLoss3  && stopLoss3>0) || (price3<=takeProfit3 && takeProfit3>0) || (price3>=liqprice3 && liqprice3>0) ){
                    double close_price3=price3;
                    double pnl = 0;
                    double quantity3=contactEntity.getQuantity3();
                    pnl = ((entryPrice3-close_price3)/entryPrice3)*quantity3*contactEntity.getLeverage3();
                    contactEntity.setWallet(wallet+pnl+quantity3);
                    contactEntity.setEntryPrice3("0");
                    contactEntity.setTakeProfit3("0");
                    contactEntity.setStopLoss3("0");
                    contactEntity.setPosition3(false);
                    contactEntity.setLiqprice3(0.0);
                    contactEntity.setQuantity3(0.0);
                    contactEntity.setNombreoftrade(contactEntity.getNombreoftrade()+1);
                    contactRepository.save(contactEntity);
                }
            }

        }
        /* **********************************************--Position4--************************************************ */

        if(contactEntity.getPosition4()==true){
            double entryPrice4=Double.parseDouble(contactEntity.getEntryPrice4());
            double takeProfit4=Double.parseDouble(contactEntity.getTakeProfit4());
            double stopLoss4=Double.parseDouble(contactEntity.getStopLoss4());
            double price4 = findPrice(contactEntity.getPair4());
            if (contactEntity.getLongorshort4()==true ){
                if((price4<=stopLoss4 &&  stopLoss4>0) || (price4>=takeProfit4 && takeProfit4>0 ) || (price4<=liqprice4 && liqprice4>0) ){
                    double close_price4=price4;
                    double pnl = 0;
                    double quantity4=contactEntity.getQuantity4();  //Maker fee
                    pnl = ((close_price4-entryPrice4)/entryPrice4)*quantity4*contactEntity.getLeverage4();
                    contactEntity.setWallet(wallet+pnl+quantity4);
                    contactEntity.setEntryPrice4("0");
                    contactEntity.setTakeProfit4("0");
                    contactEntity.setStopLoss4("0");
                    contactEntity.setPosition4(false);
                    contactEntity.setLiqprice4(0.0);
                    contactEntity.setQuantity4(0.0);
                    contactEntity.setNombreoftrade(contactEntity.getNombreoftrade()+1);
                    contactRepository.save(contactEntity);
                }
            }
            if (contactEntity.getLongorshort4()==false){
                if((price4>=stopLoss4  && stopLoss4>0) || (price4<=takeProfit4 && takeProfit4>0) || (price4>=liqprice4 && liqprice4>0) ){
                    double close_price4=price4;
                    double pnl = 0;
                    double quantity4=contactEntity.getQuantity4();
                    pnl = ((entryPrice4-close_price4)/entryPrice4)*quantity4*contactEntity.getLeverage4();
                    contactEntity.setWallet(wallet+pnl+quantity4);
                    contactEntity.setEntryPrice4("0");
                    contactEntity.setTakeProfit4("0");
                    contactEntity.setStopLoss4("0");
                    contactEntity.setPosition4(false);
                    contactEntity.setLiqprice4(0.0);
                    contactEntity.setQuantity4(0.0);
                    contactEntity.setNombreoftrade(contactEntity.getNombreoftrade()+1);
                    contactRepository.save(contactEntity);
                }
            }
        }
        }
}
}
