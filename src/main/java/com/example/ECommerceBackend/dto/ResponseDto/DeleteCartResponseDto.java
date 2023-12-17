package com.example.ECommerceBackend.dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class DeleteCartResponseDto {

    String customerName;
    int noOfItems;
    int cartTotal;
    List<ItemResponseDto> list;
    String status;

}
