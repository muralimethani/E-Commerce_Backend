package com.example.ECommerceBackend.repository;

import com.example.ECommerceBackend.dto.RequestDto.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
