package com.jwtlagi.jwtmore.message.request;

import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignUpForm {


    @NotBlank
    @Size(min=3, max = 50)
    private String nameUser;

    @NotBlank
    @Size(min=3, max = 20)
    private Long phoneUser;

    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String emailUser;

    @NotBlank
    @Size(min=6, max = 100)
    private String passwordUser;

    private Set<String> role;


    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public Long getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(Long phoneUser) {
        this.phoneUser = phoneUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
