package com.example.ECommerceBackend.service.Impl;

import com.example.ECommerceBackend.Enum.ProductStatus;
import com.example.ECommerceBackend.dto.RequestDto.ItemRequestDto;
import com.example.ECommerceBackend.exception.InvalidCustomerException;
import com.example.ECommerceBackend.exception.InvalidProductException;
import com.example.ECommerceBackend.model.*;
import com.example.ECommerceBackend.repository.CustomerRepository;
import com.example.ECommerceBackend.repository.ItemRepository;
import com.example.ECommerceBackend.repository.ProductRepository;
import com.example.ECommerceBackend.service.ItemService;
import com.example.ECommerceBackend.service.ProductService;
import com.example.ECommerceBackend.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    public Item addItem(ItemRequestDto itemRequestDto) throws Exception {

        // Check whether customer is exist or not
        Customer customer;
        try {
            customer = customerRepository.findById(itemRequestDto.getCustomerId()).get();
        }catch (Exception e){
            throw new InvalidCustomerException("Customer is not Exist with Given Id");
        }

        // Check for Product Availability
        Product product;
        try {
            product = productRepository.findById(itemRequestDto.getProductId()).get();
        }catch (Exception e){
            throw new InvalidProductException("Product don't exist with thid ID");
        }

        // Check Product Exist with Required Quantity
        if(itemRequestDto.getRequiredQunatity() > product.getQuantity() || product.getProductStatus() != ProductStatus.AVAILABLE){
            throw new Exception("Produt is Out Of Stock");
        }

        Item item = ItemTransformer.ItemRequestToItem(itemRequestDto);
        System.out.println(customer.getCart().getItems().size());
        item.setCart(customer.getCart());
        item.setProduct(product);
        product.getItemList().add(item);
        return itemRepository.save(item);
    }


}
