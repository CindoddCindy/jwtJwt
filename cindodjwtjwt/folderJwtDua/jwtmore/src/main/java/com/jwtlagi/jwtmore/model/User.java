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
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
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

}
