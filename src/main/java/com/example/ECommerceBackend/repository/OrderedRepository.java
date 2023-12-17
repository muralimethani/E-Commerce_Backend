package com.example.ECommerceBackend.repository;

import com.example.ECommerceBackend.model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderedRepository extends JpaRepository<Ordered, Integer> {

    @Query(value = "SELECT * FROM ordered ORDER BY order_date desc limit 5", nativeQuery = true)
    List<Ordered>getRecent5orders();
}
