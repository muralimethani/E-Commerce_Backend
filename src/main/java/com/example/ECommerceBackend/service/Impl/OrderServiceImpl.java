package com.example.ECommerceBackend.service.Impl;

import com.example.ECommerceBackend.model.*;
import com.example.ECommerceBackend.service.OrderService;
import com.example.ECommerceBackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    ProductService productService;
    public Ordered placeOrder(Customer customer, Card card) throws Exception {
        Cart cart = customer.getCart();

        Ordered order = new Ordered();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));
        String maskedCardNo = generateCardNo(card.getCardNo());

        order.setCardUsed(maskedCardNo);
        order.setCustomer(customer);

        List<Item> orderItems = new ArrayList<>();
        for(Item item : cart.getItems()){
            try {
                productService.decreaseProductQuantity(item);
                orderItems.add(item);
            }catch (Exception e){
                throw new Exception("Product is Out Of Stock!!");
            }
        }
        order.setItems(orderItems);
        order.setTotalValue(cart.getTotalCost());

        return order;

    }
    public String generateCardNo(String cardNo){
        String maskedNo = "";
        for(int i=0; i<cardNo.length()-4; i++){
            maskedNo += "*";
        }
        maskedNo += cardNo.substring((cardNo.length()-4));
        return maskedNo;
    }
}
