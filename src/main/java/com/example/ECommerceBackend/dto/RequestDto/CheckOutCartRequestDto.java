package com.example.ECommerceBackend.dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CheckOutCartRequestDto {
    int customerId;
    String  cardNo;
    int cvv;

}
