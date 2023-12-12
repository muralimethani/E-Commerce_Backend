package com.example.ECommerceBackend.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="Item")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

public class Item{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;


    int requiredQuantity;

    // Child class For Order
    @ManyToOne
    @JoinColumn
    Ordered order;



    // Child for Cart
    @ManyToOne
    @JoinColumn
    Cart cart;

    // Child for Product
    @ManyToOne
    @JoinColumn
    Product product;
}
