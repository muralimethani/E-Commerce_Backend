package com.example.ECommerceBackend.dto.RequestDto;

import com.example.ECommerceBackend.Enum.ProductCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDto {

    String productName;

    int price;

    int quantity;

    int sellerId;

    ProductCategory productCategory;
}
