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
@Table(name = "seller", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "nameseller"
        }),
        @UniqueConstraint(columnNames = {
                "emailseller"
        })
})
public class Seller extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSeller;


    @NotBlank
    @Size(max = 40)
    private String nameseller;


    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    private  String emailSeller;

    @NotBlank
    @Size(max = 15)
    private  String phoneSeller;

    @NotBlank
    @Size(max = 100)
    private String passwordSeller;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "seller_roles",
            joinColumns = @JoinColumn(name = "seller_id"),
            inverseJoinColumns = @JoinColumn(name = "seller_role_id"))
    private Set<Role> roles = new HashSet<>();


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
