package com.example.ECommerceBackend.model;

import com.example.ECommerceBackend.Enum.ProductCategory;
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
@Table(name="product")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    int price;

    int quantity;

    @Enumerated(EnumType.STRING)
    ProductCategory category;

    // Child for Seller
    @ManyToOne
    @JoinColumn
    Seller seller;
}
