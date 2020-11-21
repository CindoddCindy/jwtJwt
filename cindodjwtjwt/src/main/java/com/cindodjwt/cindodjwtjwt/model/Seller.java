package com.cindodjwt.cindodjwtjwt.model;

public class Seller {

    private Long idSeller;

    private String nameseller;

    private  String emailSeller;

    private  String phoneSeller;

    private String passwordSeller;

    public Seller(Long idSeller, String nameseller, String emailSeller, String phoneSeller, String passwordSeller) {
        this.idSeller = idSeller;
        this.nameseller = nameseller;
        this.emailSeller = emailSeller;
        this.phoneSeller = phoneSeller;
        this.passwordSeller = passwordSeller;
    }

    public Long getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(Long idSeller) {
        this.idSeller = idSeller;
    }

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
}
