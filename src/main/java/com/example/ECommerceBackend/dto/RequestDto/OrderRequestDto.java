package com.example.ECommerceBackend.dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequestDto {
    int customerId;
    int productId;
    int requiredQuantity;
    String cardNo;
    int cvv;
}
