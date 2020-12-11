package com.jwtlagi.jwtmore.security.services;

import com.jwtlagi.jwtmore.model.User;
import com.jwtlagi.jwtmore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userRepository.findUserByName(userName)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found with -> username or email : " + userName)
                );

        return UserPrinciple.build(user);

    }
}
