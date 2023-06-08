package Cryptoo.com.example.Cryptoo.entities;

import javax.persistence.*;

@Entity(name = "ResetPassword")
public class ResetPasswordEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String email;
    private int verificationCode;
    private int time;
    private int Nb_try;

    private String token1;
    private String token2;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(int verificationCode) {
        this.verificationCode = verificationCode;
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

    public String getToken1() {
        return token1;
    }

    public void setToken1(String token1) {
        this.token1 = token1;
    }

    public String getToken2() {
        return token2;
    }

    public void setToken2(String token2) {
        this.token2 = token2;
    }
}
