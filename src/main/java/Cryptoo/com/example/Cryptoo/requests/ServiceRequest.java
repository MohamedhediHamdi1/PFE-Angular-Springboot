package Cryptoo.com.example.Cryptoo.requests;

public class ServiceRequest {
    private String privateId;
    private boolean visible;
    private String name;
    private String companyId;
    private String description;
    private String more_description;
    private String category;
    private String date;
    private String image;
    private String video;
    private Double price;
    private Double review;
    private int nb_buy;
    private int nb_click;

    public String getPrivateId() {
        return privateId;
    }

    public void setPrivateId(String privateId) {
        this.privateId = privateId;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMore_description() {
        return more_description;
    }

    public void setMore_description(String more_description) {
        this.more_description = more_description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getReview() {
        return review;
    }

    public void setReview(Double review) {
        this.review = review;
    }

    public int getNb_buy() {
        return nb_buy;
    }

    public void setNb_buy(int nb_buy) {
        this.nb_buy = nb_buy;
    }

    public int getNb_click() {
        return nb_click;
    }

    public void setNb_click(int nb_click) {
        this.nb_click = nb_click;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
