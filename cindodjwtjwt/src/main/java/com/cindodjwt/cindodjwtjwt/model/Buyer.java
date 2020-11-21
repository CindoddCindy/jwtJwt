package com.cindodjwt.cindodjwtjwt.model;

public class Buyer {
    private Long idBuyer;

    private String nameBuyer;

    private String emailBuyer;

    private String phoneBuyer;

    private String passwordBuyer;

    public Buyer(Long idBuyer, String nameBuyer, String emailBuyer, String phoneBuyer, String passwordBuyer) {
        this.idBuyer = idBuyer;
        this.nameBuyer = nameBuyer;
        this.emailBuyer = emailBuyer;
        this.phoneBuyer = phoneBuyer;
        this.passwordBuyer = passwordBuyer;
    }

    public Long getIdBuyer() {
        return idBuyer;
    }

    public void setIdBuyer(Long idBuyer) {
        this.idBuyer = idBuyer;
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
