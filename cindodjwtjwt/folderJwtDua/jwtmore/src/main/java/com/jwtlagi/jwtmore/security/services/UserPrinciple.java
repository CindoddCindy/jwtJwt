package com.jwtlagi.jwtmore.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jwtlagi.jwtmore.model.User;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long idUser;

    private String nameUser;

    private Long phoneUser;

    private String emailUser;

    @JsonIgnore
    private String passwordUser;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(Long idUser, String nameUser, Long phoneUser, String emailUser, String passwordUser, Collection<? extends GrantedAuthority> authorities) {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.phoneUser = phoneUser;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
        this.authorities = authorities;
    }

    public UserPrinciple(Long idUser, String nameUser, String emailUser, Long phoneUser, String passwordUser, List<GrantedAuthority> authorities) {
    }

    public static UserPrinciple build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getNameRole().name())
        ).collect(Collectors.toList());

        return new UserPrinciple(
                user.getIdUser(),
                user.getNameUser(),
                user.getEmailUser(),
                user.getPhoneUser(),
                user.getPasswordUser(),
                authorities
        );
    }

    public Long getIdUser() {
        return idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public Long getPhoneUser() {
        return phoneUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public String getPasswordUser() {
        return passwordUser;
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(idUser, user.idUser);
    }
}
