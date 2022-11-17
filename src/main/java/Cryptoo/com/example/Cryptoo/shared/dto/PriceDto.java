package Cryptoo.com.example.Cryptoo.shared.dto;

public class PriceDto {
    private long id;
    private String btcprice;
    private String ethprice;
    private String bnbprice;

    public String getBtcprice() {
        return btcprice;
    }

    public void setBtcprice(String btcprice) {
        this.btcprice = btcprice;
    }

    public String getEthprice() {
        return ethprice;
    }

    public void setEthprice(String ethprice) {
        this.ethprice = ethprice;
    }

    public String getBnbprice() {
        return bnbprice;
    }

    public void setBnbprice(String bnbprice) {
        this.bnbprice = bnbprice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
