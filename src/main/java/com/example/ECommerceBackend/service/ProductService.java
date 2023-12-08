package com.example.ECommerceBackend.service;

import com.example.ECommerceBackend.Enum.ProductCategory;
import com.example.ECommerceBackend.dto.RequestDto.ProductRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.DeleteProductResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.ProductResponseDto;
import com.example.ECommerceBackend.exception.InvalidSellerException;

import java.util.List;

public interface ProductService {
    ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException;

    List<ProductResponseDto> getAllProductsByCategory(ProductCategory category);

    List<ProductResponseDto>  productsBySellerEmail(String emailId) throws Exception;

    DeleteProductResponseDto deleteProductBySellerAndProductId(int sid, int pid) throws Exception;

    List<ProductResponseDto> getAllAvailableProducts() throws Exception;

    List<ProductResponseDto> getProductWithQuantityLessThan10() throws Exception;

    List<ProductResponseDto> getCheapestProducts() throws Exception;

    List<ProductResponseDto> getAllProductsByPriceAndCategory(int price, String category);

    List<ProductResponseDto> top5CostliestProducts();

    ProductResponseDto cheapestProductInCategory(String category);

    ProductResponseDto costliestProductInCategory(String productCategory);
}
