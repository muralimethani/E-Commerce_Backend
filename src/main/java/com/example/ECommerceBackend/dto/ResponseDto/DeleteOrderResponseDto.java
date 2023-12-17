package com.example.ECommerceBackend.dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class DeleteOrderResponseDto {
    int id;
    String orderNo;
    int totalValue;
    Date orderDate;
    String cardUsed;
    String message;

}
