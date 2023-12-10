package com.example.ECommerceBackend.dto.RequestDto;

import com.example.ECommerceBackend.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardRequestDto {

    String mobile;

    int cvv;

    String cardNo;

    Date expiryDate;

    CardType cardType;

}
