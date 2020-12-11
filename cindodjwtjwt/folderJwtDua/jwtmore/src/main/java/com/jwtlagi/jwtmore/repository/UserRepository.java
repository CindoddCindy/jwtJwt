package com.jwtlagi.jwtmore.repository;

import com.jwtlagi.jwtmore.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByName(String nameUser);
    Boolean userExistByName(String nameUser);
    Boolean userExistByEmail(String emailUser);


}
