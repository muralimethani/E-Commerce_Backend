package com.example.ECommerceBackend.dto.ResponseDto;


import com.example.ECommerceBackend.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {

    String productName;

    String sellerName;

    int quantity;

    ProductStatus productStatus;
}
