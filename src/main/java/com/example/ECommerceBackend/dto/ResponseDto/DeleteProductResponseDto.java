package com.example.ECommerceBackend.dto.ResponseDto;

import com.example.ECommerceBackend.Enum.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeleteProductResponseDto {
    String name;

    int price;

    ProductCategory productCategory;

    String status;
}
