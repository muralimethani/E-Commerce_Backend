package com.example.ECommerceBackend.repository;

import com.example.ECommerceBackend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
