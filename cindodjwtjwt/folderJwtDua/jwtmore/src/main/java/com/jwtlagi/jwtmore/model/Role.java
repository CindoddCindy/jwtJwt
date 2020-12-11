package com.jwtlagi.jwtmore.model;




import org.hibernate.annotations.NaturalId;

import javax.persistence.*;


@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName roleName;

    public Role() {
    }

    public Role(RoleName roleName) {
        this.roleName = roleName;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public RoleName getNameRole() {
        return roleName;
    }

    public void setNameRole(RoleName roleName) {
        this.roleName = roleName;
    }
}
