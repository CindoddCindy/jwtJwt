package com.cindodjwt.cindodjwtjwt.security;

import com.cindodjwt.cindodjwtjwt.model.Buyer;
import com.cindodjwt.cindodjwtjwt.model.Seller;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BuyerPrincipal implements UserDetails {

    private Long idBuyer;

    private String nameBuyer;

    @JsonIgnore
    private String emailBuyer;


    private String phoneBuyer;

    @JsonIgnore
    private String passwordBuyer;

    private Collection<? extends GrantedAuthority> authorities;

    public BuyerPrincipal(Long idBuyer, String nameBuyer, String emailBuyer, String phoneBuyer, String passwordBuyer, Collection<? extends GrantedAuthority> authorities) {
        this.idBuyer = idBuyer;
        this.nameBuyer = nameBuyer;
        this.emailBuyer = emailBuyer;
        this.phoneBuyer = phoneBuyer;
        this.passwordBuyer = passwordBuyer;
        this.authorities = authorities;
    }

    public static BuyerPrincipal createBuyer(Buyer buyer) {
        List<GrantedAuthority> authorities = buyer.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new BuyerPrincipal(
                buyer.getIdBuyer(),
                buyer.getNameBuyer(),
                buyer.getEmailBuyer(),
                buyer.getPhoneBuyer(),
                buyer.getPasswordBuyer(),
                authorities
        );
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

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
