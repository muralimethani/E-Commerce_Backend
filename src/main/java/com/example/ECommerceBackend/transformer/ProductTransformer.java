package com.example.ECommerceBackend.transformer;

import com.example.ECommerceBackend.Enum.ProductCategory;
import com.example.ECommerceBackend.Enum.ProductStatus;
import com.example.ECommerceBackend.dto.RequestDto.ProductRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.DeleteProductResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.ProductResponseDto;
import com.example.ECommerceBackend.model.Product;

public class ProductTransformer {
    public static Product ProductRequestToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .name(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .productCategory(productRequestDto.getProductCategory())
                .build();
    }

    public static ProductResponseDto ProductToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .productName(product.getName())
                .sellerName(product.getSeller().getName())
                .quantity(product.getQuantity())
                .productStatus(product.getProductStatus())
                .build();
    }

    public static DeleteProductResponseDto productResponseDto(Product product){
        return DeleteProductResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .productCategory(product.getProductCategory())
                .status("Product DELETED successfully")
                .build();
    }
}
