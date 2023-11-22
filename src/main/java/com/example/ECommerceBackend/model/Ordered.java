package com.example.ECommerceBackend.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="order")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Ordered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String orderNo;

    int totalValue;

    Date orderDate;

    String cardUsed;

    // Parent for Item
    @OneToMany(mappedBy ="order", cascade = CascadeType.ALL)
    List<Item> items = new ArrayList<>();

    // Child for Customer
    @ManyToOne
    @JoinColumn
    Customer customer;
}
