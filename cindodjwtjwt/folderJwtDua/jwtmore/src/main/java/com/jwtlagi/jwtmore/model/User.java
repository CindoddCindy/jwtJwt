package com.jwtlagi.jwtmore.model;

import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "nameUser"
        }),
        @UniqueConstraint(columnNames = {
                "emailUser"
        })
})


public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User(@NotBlank @Size(min = 3, max = 50) String nameUser, @NotBlank @Size(min = 3, max = 20) Long phoneUser, @NotBlank @Size(max = 50) @Email String emailUser, @NotBlank @Size(min = 6, max = 100) String passwordUser) {
        this.nameUser = nameUser;
        this.phoneUser = phoneUser;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
    }

    public User(String nameUser, String emailUser, Long phoneUser, String encode) {
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
