package com.cindodjwt.cindodjwtjwt.payload;

import org.hibernate.annotations.NaturalId;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpRequest {

    @NotBlank
    @Size(min = 4, max = 40)
    private String nameseller;


    @NotBlank
    @Size(min = 4, max = 40)
    @Email
    private  String emailSeller;


    @NotBlank
    @Size(min = 4, max = 40)
    private  String phoneSeller;


    @NotBlank
    @Size(min = 4, max = 40)
    private String passwordSeller;

    //buyer

    @NotBlank
    @Size(min = 4, max = 40)
    private String nameBuyer;

    @NotBlank
    @Size(min = 4, max = 40)
    @Email
    private String emailBuyer;

    @NotBlank
    @Size(min = 4, max = 40)
    private String phoneBuyer;

    @NotBlank
    @Size(min = 4, max = 40)
    private String passwordBuyer;


    public String getNameseller() {
        return nameseller;
    }

    public void setNameseller(String nameseller) {
        this.nameseller = nameseller;
    }

    public String getEmailSeller() {
        return emailSeller;
    }

    public void setEmailSeller(String emailSeller) {
        this.emailSeller = emailSeller;
    }

    public String getPhoneSeller() {
        return phoneSeller;
    }

    public void setPhoneSeller(String phoneSeller) {
        this.phoneSeller = phoneSeller;
    }

    public String getPasswordSeller() {
        return passwordSeller;
    }

    public void setPasswordSeller(String passwordSeller) {
        this.passwordSeller = passwordSeller;
    }

    public String getNameBuyer() {
        return nameBuyer;
    }

    public void setNameBuyer(String nameBuyer) {
        this.nameBuyer = nameBuyer;
    }

    public String getEmailBuyer() {
        return emailBuyer;
    }

    public void setEmailBuyer(String emailBuyer) {
        this.emailBuyer = emailBuyer;
    }

    public String getPhoneBuyer() {
        return phoneBuyer;
    }

    public void setPhoneBuyer(String phoneBuyer) {
        this.phoneBuyer = phoneBuyer;
    }

    public String getPasswordBuyer() {
        return passwordBuyer;
    }

    public void setPasswordBuyer(String passwordBuyer) {
        this.passwordBuyer = passwordBuyer;
    }
}
