package com.example.ECommerceBackend.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cart")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    Integer totalCost;

    Integer numberOfItems;

    // Cart is Child for Customer
    @OneToOne
    @JoinColumn
    Customer customer;

    // Parent for Item
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    List<Item> items = new ArrayList<>();

}
