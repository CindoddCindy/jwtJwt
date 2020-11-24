package com.cindodjwt.cindodjwtjwt.repository;

import com.cindodjwt.cindodjwtjwt.model.Role;
import com.cindodjwt.cindodjwtjwt.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
