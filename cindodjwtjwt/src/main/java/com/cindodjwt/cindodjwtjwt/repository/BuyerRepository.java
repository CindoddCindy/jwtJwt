package com.cindodjwt.cindodjwtjwt.repository;

import com.cindodjwt.cindodjwtjwt.model.Buyer;
import com.cindodjwt.cindodjwtjwt.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BuyerRepository  extends JpaRepository<Buyer, Long> {

    Optional<Buyer> findBuyerByEmail(String emailBuyer);

    Optional<Buyer> findBuyerByUsernameOrEmail(String nameBuyer, String emailBuyer);

    List<Buyer> findBuyerById(List<Long> buyerUserId);

    Optional<Buyer> findBuyerByName(String nameBuyer);

    Boolean buyerExistsByName(String nameBuyer);

    Boolean buyerExistsByEmail(String emailBuyer);
}
