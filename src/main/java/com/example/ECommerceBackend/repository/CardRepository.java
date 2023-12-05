package com.example.ECommerceBackend.repository;

import com.example.ECommerceBackend.dto.RequestDto.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
}
