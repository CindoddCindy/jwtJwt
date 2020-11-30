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
    private String nameSeller;


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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "customer_roles",
            joinColumns = @JoinColumn(name = "seller_id"),
            inverseJoinColumns = @JoinColumn(name = "seller_role_id"))
    private Set<Role> roles = new HashSet<>();


    public Seller(Long idSeller, String nameSeller, String emailSeller, String phoneSeller, String passwordSeller) {
        this.idSeller = idSeller;
        this.nameSeller = nameSeller;
        this.emailSeller = emailSeller;
        this.phoneSeller = phoneSeller;
        this.passwordSeller = passwordSeller;
    }

    public Seller(@NotBlank @Size(max = 40) String nameSeller, @NotBlank @Size(max = 40) @Email String emailSeller, @NotBlank @Size(max = 15) String phoneSeller, @NotBlank @Size(max = 100) String passwordSeller) {
        this.nameSeller = nameSeller;
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
        return nameSeller;
    }

    public void setNameseller(String nameSeller) {
        this.nameSeller = nameSeller;
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
