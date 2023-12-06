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
@Table(name ="seller")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    @Column(unique = true)
    String emailId;

    Integer age;

    String mobNo;

    // Parent for Product
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    List<Product> products = new ArrayList<>();

}
