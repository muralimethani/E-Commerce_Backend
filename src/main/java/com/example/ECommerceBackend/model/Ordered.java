package com.example.ECommerceBackend.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ordered")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

public class Ordered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String orderNo;

    int totalValue;

    @CreationTimestamp
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
