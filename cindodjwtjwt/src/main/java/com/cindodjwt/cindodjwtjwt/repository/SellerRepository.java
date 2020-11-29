package com.cindodjwt.cindodjwtjwt.repository;

import com.cindodjwt.cindodjwtjwt.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    Optional<Seller> findSellerByEmail(String emailSeller);

    Optional<Seller> findSellerByUsernameOrEmail(String nameSeller, String emailSeller);

    List<Seller> findSellerById(List<Long> sellerUserId);

    Optional<Seller> findSellerByName(String nameSeller);

    Boolean sellerExistsByName(String nameSeller);

    Boolean sellerExistsByEmail(String emailSeller);
}
