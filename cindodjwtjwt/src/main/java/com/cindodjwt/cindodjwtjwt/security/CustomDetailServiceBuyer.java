package com.cindodjwt.cindodjwtjwt.security;

import com.cindodjwt.cindodjwtjwt.model.Buyer;
import com.cindodjwt.cindodjwtjwt.model.Seller;
import com.cindodjwt.cindodjwtjwt.repository.BuyerRepository;
import com.cindodjwt.cindodjwtjwt.repository.SellerRepository;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomDetailServiceBuyer implements UserDetailsService {

    @Autowired
    BuyerRepository buyerRepository;// dari repo


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // Let people login with either username or email
        Buyer buyer = buyerRepository.findBuyerByUsernameOrEmail(s, s)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + s)
                );

        return BuyerPrincipal.createBuyer(buyer);
       // return null;
    }





    @Transactional
    public UserDetails loadUserById(Long id) {
        Buyer buyer = buyerRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return BuyerPrincipal.createBuyer(buyer);
    }
}
