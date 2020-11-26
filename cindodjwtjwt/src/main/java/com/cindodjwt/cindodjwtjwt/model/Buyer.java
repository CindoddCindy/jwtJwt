package com.cindodjwt.cindodjwtjwt.model;

import com.cindodjwt.cindodjwtjwt.model.audit.DateAudit;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "buyer", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "namebuyer"
        }),
        @UniqueConstraint(columnNames = {
                "emailbuyer"
        })
})
public class Buyer extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBuyer;

    @NotBlank
    @Size(max = 40)
    private String nameBuyer;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    private String emailBuyer;

    @NotBlank
    @Size(max = 15)
    private String phoneBuyer;

    @NotBlank
    @Size(max = 100)
    private String passwordBuyer;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "customer_roles",
            joinColumns = @JoinColumn(name = "buyer_id"),
            inverseJoinColumns = @JoinColumn(name = "buyer_role_id"))
    private Set<Role> roles = new HashSet<>();


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
