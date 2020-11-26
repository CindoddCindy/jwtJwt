package com.cindodjwt.cindodjwtjwt.security;

import com.cindodjwt.cindodjwtjwt.model.Seller;
import com.cindodjwt.cindodjwtjwt.repository.SellerRepository;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class CustomDetailServiceSeller implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
