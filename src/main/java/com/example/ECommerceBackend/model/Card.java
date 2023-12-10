package com.example.ECommerceBackend.model;


import com.example.ECommerceBackend.Enum.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="card")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int cvv;

    @Column(unique = true, nullable = false)
    String cardNo;

    Date expiryDate;

    @Enumerated(EnumType.STRING)
    CardType cardType;

    // Child class for Customer class
    @ManyToOne
    @JoinColumn
    Customer customer;

}
