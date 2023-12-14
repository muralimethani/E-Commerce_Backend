package com.example.ECommerceBackend.repository;

import com.example.ECommerceBackend.model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedRepository extends JpaRepository<Ordered, Integer> {
}
