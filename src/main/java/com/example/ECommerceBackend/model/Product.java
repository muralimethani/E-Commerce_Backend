package com.example.ECommerceBackend.model;

import com.example.ECommerceBackend.Enum.ProductCategory;
import com.example.ECommerceBackend.Enum.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    int price;

    int quantity;

    @Enumerated(EnumType.STRING)
    ProductCategory productCategory;

    @Enumerated(EnumType.STRING)
    ProductStatus productStatus;

    // Child for Seller
    @ManyToOne
    @JoinColumn
    Seller seller;

    // Parent for Item
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<Item> itemList = new ArrayList<>();
}
