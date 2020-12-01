package com.cindodjwt.cindodjwtjwt.controller;


import com.cindodjwt.cindodjwtjwt.exception.AppException;
import com.cindodjwt.cindodjwtjwt.model.Buyer;
import com.cindodjwt.cindodjwtjwt.model.Role;
import com.cindodjwt.cindodjwtjwt.model.RoleName;
import com.cindodjwt.cindodjwtjwt.model.Seller;
import com.cindodjwt.cindodjwtjwt.payload.ApiResponse;
import com.cindodjwt.cindodjwtjwt.payload.JwtAuthenticationResponse;
import com.cindodjwt.cindodjwtjwt.payload.LoginRequest;
import com.cindodjwt.cindodjwtjwt.payload.SignUpRequest;
import com.cindodjwt.cindodjwtjwt.repository.BuyerRepository;
import com.cindodjwt.cindodjwtjwt.repository.RoleRepository;
import com.cindodjwt.cindodjwtjwt.repository.SellerRepository;
import com.cindodjwt.cindodjwtjwt.security.BuyerTokenProvider;
import com.cindodjwt.cindodjwtjwt.security.SellerTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.Optional;

@CrossOrigin("http://localhost:8082")
@RestController
@RequestMapping("/api/authseller")
public class SellerController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SellerTokenProvider sellerTokenProvider;

    @PostMapping("/signinseller")
    public ResponseEntity<?> authenticateSeller(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = sellerTokenProvider.generateSellerToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    private String getJwt(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }

        return null;
    }

    @GetMapping("/seller")
    public ResponseEntity<?> seller(HttpServletRequest request) {
        String bearerToken = this.getJwt(request);
        if (bearerToken == null) {
            return ResponseEntity.status(403).body("akses tidak diizinkan.");
        }

        long userId = sellerTokenProvider.getSellerIdFromJWT(bearerToken);
        Optional<Seller> seller = sellerRepository.findById(userId);

        return ResponseEntity.ok(seller);
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerSeller(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(sellerRepository.sellerExistsByName(signUpRequest.getNameSeller())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(sellerRepository.sellerExistsByEmail(signUpRequest.getEmailSeller())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        Seller seller = new Seller(signUpRequest.getNameSeller(), signUpRequest.getEmailSeller(),
                signUpRequest.getPhoneSeller(), signUpRequest.getPasswordSeller());

        seller.setPasswordSeller(passwordEncoder.encode(seller.getPasswordSeller()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_SELLER)
                .orElseThrow(() -> new AppException("User Role not set."));

        seller.setRoles(Collections.singleton(userRole));

        Seller result = sellerRepository.save(seller);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/seller/{nameSeller}")
                .buildAndExpand(result.getNameSeller()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

}
