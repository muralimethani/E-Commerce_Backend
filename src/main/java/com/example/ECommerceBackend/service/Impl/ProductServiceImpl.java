package com.example.ECommerceBackend.service.Impl;

import com.example.ECommerceBackend.Enum.ProductCategory;
import com.example.ECommerceBackend.Enum.ProductStatus;
import com.example.ECommerceBackend.dto.RequestDto.ProductRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.DeleteProductResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.ProductResponseDto;
import com.example.ECommerceBackend.exception.InvalidSellerException;
import com.example.ECommerceBackend.model.Item;
import com.example.ECommerceBackend.model.Product;
import com.example.ECommerceBackend.model.Seller;
import com.example.ECommerceBackend.repository.ProductRepository;
import com.example.ECommerceBackend.repository.SellerRepository;
import com.example.ECommerceBackend.service.ProductService;
import com.example.ECommerceBackend.transformer.ProductTransformer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;

    public ProductServiceImpl(SellerRepository sellerRepository,
                              ProductRepository productRepository) {
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
    }

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException {
        Seller seller;
        try{
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();

        }catch (Exception e){
            throw new InvalidSellerException("Seller not exist");
        }

        // Generate Product with transformers
        Product product = ProductTransformer.ProductRequestToProduct(productRequestDto);
        product.setSeller(seller);

        // add product to current product of seller
        seller.getProducts().add(product);
        sellerRepository.save(seller);

        // Get Product ResponseDto
        ProductResponseDto productResponseDto = ProductTransformer.ProductToProductResponseDto(product);
        return productResponseDto;
    }

    public List<ProductResponseDto> getAllProductsByCategory(ProductCategory category){
        List<Product>products = productRepository.findByProductCategory(category);

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        for(Product product : products){
            productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));
        }

        return productResponseDtos;
    }

    public List<ProductResponseDto> productsBySellerEmail(String emailId) throws Exception{
        Seller seller;
        try {
            seller = sellerRepository.findByEmailId(emailId);
        }
        catch (Exception e){
            throw new Exception("No products with this Sellers EmailId");
        }

        List<Product> products = seller.getProducts();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product : products){
            productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));
        }
        return productResponseDtos;
    }

    @Override
    public DeleteProductResponseDto deleteProductBySellerAndProductId(int sid, int pid) throws Exception {
        Seller seller;
        try {
            seller = sellerRepository.findById(pid).get();
        }
        catch (Exception e){
            throw new InvalidSellerException("Seller not Present with this ID");
        }
        Product product;

        try {
            product = productRepository.findById(pid).get();
        }
        catch (Exception e){
            throw new Exception("Product is not present with this ID");
        }

        List<Product> products = seller.getProducts();
        boolean b = false;
        for(Product product1 : products){
            if(product1.getId()==pid){
                b= true;
                break;
            }
        }

        if(!b) throw new Exception("Seller has no product with this product id");

        products.remove(product);
        productRepository.delete(product);
        sellerRepository.save(seller);

        DeleteProductResponseDto deleteProductResponseDto = ProductTransformer.productResponseDto(product);
        return deleteProductResponseDto;
    }

    public List<ProductResponseDto> getAllAvailableProducts() throws Exception{
        List<Product> products = productRepository.findAll();
        List<Product> availableProducts = new ArrayList<>();

        for(Product product : products){
            if(product.getProductStatus() == ProductStatus.AVAILABLE){
                availableProducts.add(product);
            }
        }
        if(availableProducts.isEmpty()){
            throw new Exception("Now Products available");
        }

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product : availableProducts){
            productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));
        }

        return productResponseDtos;
    }

    public List<ProductResponseDto> getProductWithQuantityLessThan10() throws Exception{
        List<Product> products = productRepository.findAll();
        List<Product> productsWithLessTtan10 = new ArrayList<>();
        for(Product p : products){
            if(p.getQuantity()<=10){
                productsWithLessTtan10.add(p);
            }
        }

        if(productsWithLessTtan10.isEmpty()){
            throw new Exception("Oops! No products exists with < 10");
        }

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product p : productsWithLessTtan10){
            productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(p));
        }
        return productResponseDtos;
    }

    public List<ProductResponseDto> getCheapestProducts() throws Exception{
        List<Product>top5CheapestProducts = new ArrayList<>();
        top5CheapestProducts = productRepository.get5CheapestProducts();

        List<ProductResponseDto> productResponseDtoArrayList = new ArrayList<>();
        for(Product p : top5CheapestProducts){
            productResponseDtoArrayList.add(ProductTransformer.ProductToProductResponseDto(p));
        }

        return productResponseDtoArrayList;
    }

    public List<ProductResponseDto> getAllProductsByPriceAndCategory(int price, String category){
        List<Product> products = productRepository.getAllProductsByPriceAndCategory(price, category);
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product p : products){
            productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(p));
        }
        return productResponseDtos;
    }

    public List<ProductResponseDto> top5CostliestProducts(){
        List<Product> costlyProducts  = new ArrayList<>();
        costlyProducts  = productRepository.top5CostliestProducts();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product p: costlyProducts ){
            productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(p));
        }
        return productResponseDtos;
    }

    public ProductResponseDto cheapestProductInCategory(String category){
        Product cheapProduct = productRepository.getCheaperProductInTheCategory(category);
        return ProductTransformer.ProductToProductResponseDto(cheapProduct);
    }

    public ProductResponseDto costliestProductInCategory(String productCategory){
        Product costliestProduct = productRepository.getCostliestProductInCategory(productCategory);
        ProductResponseDto costliestProductResponseDto = ProductTransformer.ProductToProductResponseDto(costliestProduct);
        return  costliestProductResponseDto;
    }

    public void decreaseProductQuantity(Item item) throws Exception {
        Product product = item.getProduct();
        int currentQuantity = product.getQuantity();
        int quantity = item.getRequiredQuantity();
        if(quantity > currentQuantity){
            throw new Exception("Out of Stock");
        }

        product.setQuantity(currentQuantity-quantity);
        if(product.getQuantity()==0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }
    }
}
