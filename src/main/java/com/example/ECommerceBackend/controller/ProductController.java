package com.example.ECommerceBackend.controller;

import com.example.ECommerceBackend.Enum.ProductCategory;
import com.example.ECommerceBackend.Enum.ProductStatus;
import com.example.ECommerceBackend.dto.RequestDto.ProductRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.DeleteProductResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.ProductResponseDto;
import com.example.ECommerceBackend.repository.ProductRepository;
import com.example.ECommerceBackend.service.ProductService;
import com.example.ECommerceBackend.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto) throws InterruptedException{
        try {
            ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);
            return new ResponseEntity(productResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{category}")
    public List<ProductResponseDto> getAllProductsByCategory(@PathVariable("category")ProductCategory category){
        return productService.getAllProductsByCategory(category);
    }

    // Get all product by seller email id
    @GetMapping("/withSellerEmail")
    public ResponseEntity productsBySellerEmail(@RequestParam("emailId") String emailId) throws Exception{
        try {
            List<ProductResponseDto> list = productService.productsBySellerEmail(emailId);
            return new ResponseEntity(list, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Delete Product by Seller id and Product Id
    @DeleteMapping("/deleteBySellerProductId")
    public ResponseEntity deleteProductBySellerAndProductId(@RequestParam("sid") int sid, @RequestParam("pid") int pid) throws Exception{
        try {
            DeleteProductResponseDto deleteProductResponseDto = productService.deleteProductBySellerAndProductId(sid, pid);
            return new ResponseEntity(deleteProductResponseDto, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Get all available products
    @GetMapping("/getAllAvailable")
    public ResponseEntity getAllAvailableProducts() throws  Exception{
        try{
            List<ProductResponseDto> listOfAvailableProducts = productService.getAllAvailableProducts();
            return new ResponseEntity(listOfAvailableProducts, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    // Get products List which have quantity < 10
    @GetMapping("/productsWithQuantiyLessThan10")
    public List<ProductResponseDto> getProductWithQuantityLessThan10() throws Exception{
        return productService.getProductWithQuantityLessThan10();
    }

    // Get Top 5 Cheapest Products
    @GetMapping("/top5CheapestProducts")
    public List<ProductResponseDto> get5CheapestProducts() throws Exception{
        return productService.getCheapestProducts();
    }

    // getAllProductsByPriceAndCategory
    @GetMapping("/get/{price}/{category}")
    public List<ProductResponseDto> getAllProductsByPriceAndCategory(@PathVariable("price") int price, @PathVariable("category")String  category){
        return productService.getAllProductsByPriceAndCategory(price, category);
    }

    // Get Top 5 Costliest Products
    @GetMapping("/top5Costliest")
    public List<ProductResponseDto> top5CostliestProducts(){
        return productService.top5CostliestProducts();
    }

    // Cheapest Product in given Category
    @GetMapping("/cheapProductInCategory")
    public ProductResponseDto cheapestProductInCategory(@RequestParam("category") String category){
        return productService.cheapestProductInCategory(category);
    }

    // Costliest Product in Category
    @GetMapping("/costliestProductInCategory")
    public ProductResponseDto costliestProductInCategory(@RequestParam("productCategory") String productCategory){
        return productService.costliestProductInCategory(productCategory);
    }
}
