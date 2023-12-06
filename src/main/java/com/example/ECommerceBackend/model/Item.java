package com.example.ECommerceBackend.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="Item")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Item{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    int requiredQuantity;

    // Child class For Order
    @ManyToOne
    @JoinColumn
    Ordered order;



    // Child for Cart
    @ManyToOne
    @JoinColumn
    Cart cart;
}
