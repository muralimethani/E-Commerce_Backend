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
@Table(name="customer")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    String emailId;

    Integer age;

    String mobile;

    String address;

    // Parent class for Card
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<Card> cards = new ArrayList<>();

    // Parent class for Cart
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    Cart cart;

    // Parent for Order
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<Ordered> orderList = new ArrayList<>();
}
