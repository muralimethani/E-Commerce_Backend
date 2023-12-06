package com.example.ECommerceBackend.repository;

import com.example.ECommerceBackend.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
    Seller findByEmailId(String email);
}
