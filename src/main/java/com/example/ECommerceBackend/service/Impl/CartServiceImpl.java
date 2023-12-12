package com.example.ECommerceBackend.service.Impl;

import com.example.ECommerceBackend.dto.ResponseDto.CartResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.ItemResponseDto;
import com.example.ECommerceBackend.model.Cart;
import com.example.ECommerceBackend.model.Customer;
import com.example.ECommerceBackend.model.Item;
import com.example.ECommerceBackend.repository.CardRepository;
import com.example.ECommerceBackend.repository.CartRepository;
import com.example.ECommerceBackend.repository.CustomerRepository;
import com.example.ECommerceBackend.repository.ProductRepository;
import com.example.ECommerceBackend.service.CartService;
import com.example.ECommerceBackend.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;
//    @Autowired
//    CartRepository cartRepository;

    public CartResponseDto saveCart(int customerId, Item item){
//        int cartTotal;
//        String customerName;
//        int noOfItems;
//        List<ItemResponseDto> items;

        Customer customer = customerRepository.findById(customerId).get();
        Cart cart = customer.getCart();

        int newTotal = cart.getTotalCost() + item.getProduct().getPrice() * item.getRequiredQuantity();
        cart.setTotalCost(newTotal);
        cart.getItems().add(item);
        cart.setNumberOfItems(cart.getItems().size());
        Cart savedCart = cartRepository.save(cart);

        CartResponseDto cartResponseDto = CartResponseDto.builder()
                .customerName(customer.getName())
                .noOfItems(cart.getNumberOfItems())
                .cartTotal(cart.getTotalCost())
                .build();

        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        for(Item item1 : cart.getItems()){
            itemResponseDtos.add(ItemTransformer.ItemToItemResponseDto(item1));
        }

        cartResponseDto.setItems(itemResponseDtos);
        return cartResponseDto;
    }
}
