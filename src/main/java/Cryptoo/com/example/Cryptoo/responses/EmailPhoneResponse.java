package Cryptoo.com.example.Cryptoo.responses;

public class EmailPhoneResponse {


    private String email;
    private String phone;
    private int emailCode;
    private int phoneCode;
    private int time;
    private int Nb_try;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(int emailCode) {
        this.emailCode = emailCode;
    }

    public int getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(int phoneCode) {
        this.phoneCode = phoneCode;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getNb_try() {
        return Nb_try;
    }

    public void setNb_try(int nb_try) {
        Nb_try = nb_try;
    }

}
