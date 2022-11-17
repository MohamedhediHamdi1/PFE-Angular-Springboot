package Cryptoo.com.example.Cryptoo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity(name = "contacts")
public class ContactEntity implements Serializable {

    private static final long serialVersionUID = 5127045241493126947L;

    @Id
    @GeneratedValue
    private long idd;

    @NotNull
    @Column(length = 30)
    private String contactId;


    private Boolean order1;

    private Boolean order2;

    private Boolean order3;

    private Boolean position1;

    private Boolean position2;

    private Boolean position3;

    private String entryPrice1;
    private String entryPrice2;
    private String entryPrice3;
    private String takeProfit1;
    private String takeProfit2;
    private String takeProfit3;
    private String stopLoss1;
    private String stopLoss2;
    private String stopLoss3;

    private String pair1;
    private Double leverage1;
    private String pair2;
    private Double leverage2;
    private String pair3;
    private Double leverage3;

    private Boolean longorshort1;
    private Boolean longorshort2;
    private Boolean longorshort3;
    private Double quantity1;
    private Double quantity2;
    private Double quantity3;
    private Double liqprice1;
    private Double liqprice2;
    private Double liqprice3;

    private String order1time;
    private String order2time;
    private String order3time;

    private Double wallet;

    private int nombreoftrade;

    public int getNombreoftrade() {
        return nombreoftrade;
    }

    public void setNombreoftrade(int nombreoftrade) {
        this.nombreoftrade = nombreoftrade;
    }

    public Double getWallet() {
        return wallet;
    }

    public void setWallet(Double wallet) {
        this.wallet = wallet;
    }

    @OneToOne
    @JoinColumn(name = "users_id")
    private UserEntity user;



    public Boolean getOrder1() {
        return order1;
    }

    public void setOrder1(Boolean order1) {
        this.order1 = order1;
    }

    public Boolean getOrder2() {
        return order2;
    }

    public void setOrder2(Boolean order2) {
        this.order2 = order2;
    }

    public Boolean getOrder3() {
        return order3;
    }

    public void setOrder3(Boolean order3) {
        this.order3 = order3;
    }

    public Boolean getPosition1() {
        return position1;
    }

    public void setPosition1(Boolean position1) {
        this.position1 = position1;
    }

    public Boolean getPosition2() {
        return position2;
    }

    public void setPosition2(Boolean position2) {
        this.position2 = position2;
    }

    public Boolean getPosition3() {
        return position3;
    }

    public void setPosition3(Boolean position3) {
        this.position3 = position3;
    }





    public long getIdd() {
        return idd;
    }

    public void setIdd(long id) {
        this.idd = idd;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }



    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getEntryPrice1() {
        return entryPrice1;
    }

    public void setEntryPrice1(String entryPrice1) {
        this.entryPrice1 = entryPrice1;
    }

    public String getEntryPrice2() {
        return entryPrice2;
    }

    public void setEntryPrice2(String entryPrice2) {
        this.entryPrice2 = entryPrice2;
    }

    public String getEntryPrice3() {
        return entryPrice3;
    }

    public void setEntryPrice3(String entryPrice3) {
        this.entryPrice3 = entryPrice3;
    }

    public String getTakeProfit1() {
        return takeProfit1;
    }

    public void setTakeProfit1(String takeProfit1) {
        this.takeProfit1 = takeProfit1;
    }

    public String getTakeProfit2() {
        return takeProfit2;
    }

    public void setTakeProfit2(String takeProfit2) {
        this.takeProfit2 = takeProfit2;
    }

    public String getTakeProfit3() {
        return takeProfit3;
    }

    public void setTakeProfit3(String takeProfit3) {
        this.takeProfit3 = takeProfit3;
    }

    public String getStopLoss1() {
        return stopLoss1;
    }

    public void setStopLoss1(String stopLoss1) {
        this.stopLoss1 = stopLoss1;
    }

    public String getStopLoss2() {
        return stopLoss2;
    }

    public void setStopLoss2(String stopLoss2) {
        this.stopLoss2 = stopLoss2;
    }

    public String getStopLoss3() {
        return stopLoss3;
    }

    public void setStopLoss3(String stopLoss3) {
        this.stopLoss3 = stopLoss3;
    }

    public String getPair1() {
        return pair1;
    }

    public void setPair1(String pair1) {
        this.pair1 = pair1;
    }

    public Double getLeverage1() {
        return leverage1;
    }

    public void setLeverage1(Double leverage1) {
        this.leverage1 = leverage1;
    }

    public String getPair2() {
        return pair2;
    }

    public void setPair2(String pair2) {
        this.pair2 = pair2;
    }

    public Double getLeverage2() {
        return leverage2;
    }

    public void setLeverage2(Double leverage2) {
        this.leverage2 = leverage2;
    }

    public String getPair3() {
        return pair3;
    }

    public void setPair3(String pair3) {
        this.pair3 = pair3;
    }

    public Double getLeverage3() {
        return leverage3;
    }

    public void setLeverage3(Double leverage3) {
        this.leverage3 = leverage3;
    }

    public Boolean getLongorshort1() {
        return longorshort1;
    }

    public void setLongorshort1(Boolean longorshort1) {
        this.longorshort1 = longorshort1;
    }

    public Boolean getLongorshort2() {
        return longorshort2;
    }

    public void setLongorshort2(Boolean longorshort2) {
        this.longorshort2 = longorshort2;
    }

    public Boolean getLongorshort3() {
        return longorshort3;
    }

    public void setLongorshort3(Boolean longorshort3) {
        this.longorshort3 = longorshort3;
    }

    public Double getQuantity1() {
        return quantity1;
    }

    public void setQuantity1(Double quantity1) {
        this.quantity1 = quantity1;
    }

    public Double getQuantity2() {
        return quantity2;
    }

    public void setQuantity2(Double quantity2) {
        this.quantity2 = quantity2;
    }

    public Double getQuantity3() {
        return quantity3;
    }

    public void setQuantity3(Double quantity3) {
        this.quantity3 = quantity3;
    }

    public Double getLiqprice1() {
        return liqprice1;
    }

    public void setLiqprice1(Double liqprice1) {
        this.liqprice1 = liqprice1;
    }

    public Double getLiqprice2() {
        return liqprice2;
    }

    public void setLiqprice2(Double liqprice2) {
        this.liqprice2 = liqprice2;
    }

    public Double getLiqprice3() {
        return liqprice3;
    }

    public void setLiqprice3(Double liqprice3) {
        this.liqprice3 = liqprice3;
    }

    public String getOrder1time() {
        return order1time;
    }

    public void setOrder1time(String order1time) {
        this.order1time = order1time;
    }

    public String getOrder2time() {
        return order2time;
    }

    public void setOrder2time(String order2time) {
        this.order2time = order2time;
    }

    public String getOrder3time() {
        return order3time;
    }

    public void setOrder3time(String order3time) {
        this.order3time = order3time;
    }
}
