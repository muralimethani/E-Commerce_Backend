package com.example.ECommerceBackend.dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CartItemResponseDto {

    String customerName;

    List<ItemResponseDto> list;

}
