package com.example.ECommerceBackend.repository;

import com.example.ECommerceBackend.Enum.ProductCategory;
import com.example.ECommerceBackend.dto.ResponseDto.ProductResponseDto;
import com.example.ECommerceBackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByProductCategory(ProductCategory productCategory);

    @Query(value = "select * from product order by price limit 5", nativeQuery = true)
    List<Product> get5CheapestProducts();

    @Query(value = "select * from Product p where p.price > :price and p.product_category=:category", nativeQuery = true)
    List<Product> getAllProductsByPriceAndCategory(int price, String category);

    @Query(value = "select * from Product order by price desc limit 5", nativeQuery = true)
    List<Product> top5CostliestProducts();

    @Query(value = "select * from Product WHERE product_category =:category ORDER BY price ASC limit 1", nativeQuery = true)
    Product getCheaperProductInTheCategory(String category);

    @Query(value = "SELECT * FROM Product WHERE product_category=:productCategory ORDER BY price DESC limit 1", nativeQuery = true)
    Product getCostliestProductInCategory(String productCategory);
}
