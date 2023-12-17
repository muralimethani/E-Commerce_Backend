package com.example.ECommerceBackend.dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponseDto2 {
    String customerName;
    String orderNo;
    int noOfItems;
    Date orderDate;
    int totalCost;
}
