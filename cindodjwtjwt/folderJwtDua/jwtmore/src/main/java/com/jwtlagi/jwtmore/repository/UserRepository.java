package com.jwtlagi.jwtmore.repository;

import com.jwtlagi.jwtmore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String nameUser);
    Boolean existsByUsername(String nameUser);
    Boolean existsByEmail(String emailUser);


}
