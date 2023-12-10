package com.example.ECommerceBackend.repository;

import com.example.ECommerceBackend.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    @Query(value = "SELECT * FROM card c WHERE c.expiry_Date >:expiryDate", nativeQuery = true)
    List<Card> getCardsWithExpiryDateGreaterThanGivenDate(Date expiryDate);

    @Query(value = "SELECT * FROM Card c WHERE c.expiry_Date>='2025-01-01'", nativeQuery = true)
    List<Card> getMasterCardsWithExpiryDateGreaterThan2k25();
}
