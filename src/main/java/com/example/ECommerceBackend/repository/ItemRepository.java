package com.example.ECommerceBackend.repository;

import com.example.ECommerceBackend.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
