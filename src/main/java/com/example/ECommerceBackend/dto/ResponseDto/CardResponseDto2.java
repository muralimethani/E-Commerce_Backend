package com.example.ECommerceBackend.dto.ResponseDto;

import com.example.ECommerceBackend.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardResponseDto2 {

    int cvv;
    String cardNo;
    Date expiryDate;
    CardType cardType;
    String customerName;

}
