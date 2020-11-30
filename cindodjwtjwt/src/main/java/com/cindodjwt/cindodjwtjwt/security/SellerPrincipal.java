package com.cindodjwt.cindodjwtjwt.security;

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

public class SellerPrincipal implements UserDetails {

    private Long idSeller;


    private String nameSeller;

    @JsonIgnore
    private  String emailSeller;

    private  String phoneSeller;

    @JsonIgnore
    private String passwordSeller;

    private Collection<? extends GrantedAuthority> authorities;

    public SellerPrincipal(Long idSeller, String nameSeller, String emailSeller, String phoneSeller, String passwordSeller, Collection<? extends GrantedAuthority> authorities) {
        this.idSeller = idSeller;
        this.nameSeller = nameSeller;
        this.emailSeller = emailSeller;
        this.phoneSeller = phoneSeller;
        this.passwordSeller = passwordSeller;
        this.authorities = authorities;
    }


    public static SellerPrincipal createSeller(Seller seller) {
        List<GrantedAuthority> authorities = seller.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new SellerPrincipal(
                seller.getIdSeller(),
                seller.getNameseller(),
                seller.getEmailSeller(),
                seller.getPhoneSeller(),
                seller.getPasswordSeller(),
                authorities
        );
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
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

    public void setNameseller(String nameseller) {
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
