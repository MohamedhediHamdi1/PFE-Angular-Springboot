package Cryptoo.com.example.Cryptoo.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "price")
public class PriceEntity implements Serializable {
    private static final long serialVersionUID = 5127045241497456947L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
