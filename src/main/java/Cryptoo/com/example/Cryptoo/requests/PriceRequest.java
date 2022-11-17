package Cryptoo.com.example.Cryptoo.requests;

public class PriceRequest {
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
}
