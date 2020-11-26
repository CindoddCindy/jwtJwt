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
    @Autowired
    SellerRepository sellerRepository;// dari repo



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        // Let people login with either username or email
        Seller seller = sellerRepository.findSellerByUsernameOrEmail(s, s)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + s)
                );

        return UserPrincipal.create(seller);

       // return null;
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        Seller seller = sellerRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return UserPrincipal.create(seller);
    }
}
