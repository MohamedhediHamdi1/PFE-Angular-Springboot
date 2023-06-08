package Cryptoo.com.example.Cryptoo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "updateUser")
public class UpdateUserEntity implements Serializable {

    @Id
    private String id;

    private String userId;

    private Date email;
    private Date encryptedPassword;
    private Date firstname;
    private Date lastname;
    private Date phone;
    private Date imageId;
    private Date country;
    private Date state;
    private Date city;
    private Date zipcode;
    private Date address1;
    private Date address2;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getEmail() {
        return email;
    }

    public void setEmail(Date email) {
        this.email = email;
    }

    public Date getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(Date encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Date getFirstname() {
        return firstname;
    }

    public void setFirstname(Date firstname) {
        this.firstname = firstname;
    }

    public Date getLastname() {
        return lastname;
    }

    public void setLastname(Date lastname) {
        this.lastname = lastname;
    }

    public Date getPhone() {
        return phone;
    }

    public void setPhone(Date phone) {
        this.phone = phone;
    }

    public Date getImageId() {
        return imageId;
    }

    public void setImageId(Date imageId) {
        this.imageId = imageId;
    }

    public Date getCountry() {
        return country;
    }

    public void setCountry(Date country) {
        this.country = country;
    }

    public Date getState() {
        return state;
    }

    public void setState(Date state) {
        this.state = state;
    }

    public Date getCity() {
        return city;
    }

    public void setCity(Date city) {
        this.city = city;
    }

    public Date getZipcode() {
        return zipcode;
    }

    public void setZipcode(Date zipcode) {
        this.zipcode = zipcode;
    }

    public Date getAddress1() {
        return address1;
    }

    public void setAddress1(Date address1) {
        this.address1 = address1;
    }

    public Date getAddress2() {
        return address2;
    }

    public void setAddress2(Date address2) {
        this.address2 = address2;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
