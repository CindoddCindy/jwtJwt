package com.cindodjwt.cindodjwtjwt.controller;


import com.cindodjwt.cindodjwtjwt.exception.AppException;
import com.cindodjwt.cindodjwtjwt.model.Buyer;
import com.cindodjwt.cindodjwtjwt.model.Role;
import com.cindodjwt.cindodjwtjwt.model.RoleName;
import com.cindodjwt.cindodjwtjwt.payload.ApiResponse;
import com.cindodjwt.cindodjwtjwt.payload.JwtAuthenticationResponse;
import com.cindodjwt.cindodjwtjwt.payload.LoginRequest;
import com.cindodjwt.cindodjwtjwt.payload.SignUpRequest;
import com.cindodjwt.cindodjwtjwt.repository.BuyerRepository;
import com.cindodjwt.cindodjwtjwt.repository.RoleRepository;
import com.cindodjwt.cindodjwtjwt.security.BuyerTokenProvider;
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
import javax.swing.plaf.basic.BasicButtonUI;
import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.Optional;

@CrossOrigin("http://localhost:8082")
@RestController
@RequestMapping("/api/authbuyer")
public class BuyerController {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    BuyerTokenProvider buyerTokenProvider;

    @PostMapping("/signinbuyer")
    public ResponseEntity<?> authenticateBuyer(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = buyerTokenProvider.generateBuyerToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    private String getJwt(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }

        return null;
    }

    @GetMapping("/buyer")
    public ResponseEntity<?> buyer(HttpServletRequest request) {
        String bearerToken = this.getJwt(request);
        if (bearerToken == null) {
            return ResponseEntity.status(403).body("akses tidak diizinkan.");
        }

        long userId = buyerTokenProvider.getBuyerIdFromJWT(bearerToken);
        Optional<Buyer> buyer = buyerRepository.findById(userId);

        return ResponseEntity.ok(buyer);
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerBuyer(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(buyerRepository.buyerExistsByName(signUpRequest.getNameBuyer())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(buyerRepository.buyerExistsByEmail(signUpRequest.getEmailBuyer())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        Buyer buyer = new Buyer(signUpRequest.getNameBuyer(), signUpRequest.getEmailBuyer(),
                signUpRequest.getPhoneBuyer(), signUpRequest.getPasswordBuyer());

        buyer.setPasswordBuyer(passwordEncoder.encode(buyer.getPasswordBuyer()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_BUYER)
                .orElseThrow(() -> new AppException("User Role not set."));

        buyer.setRoles(Collections.singleton(userRole));

        Buyer result = buyerRepository.save(buyer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/buyer/{nameBuyer}")
                .buildAndExpand(result.getNameBuyer()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

}
