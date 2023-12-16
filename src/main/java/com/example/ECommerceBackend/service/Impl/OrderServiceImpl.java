package com.example.ECommerceBackend.service.Impl;

import com.example.ECommerceBackend.dto.RequestDto.OrderRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.ItemResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.OrderResponseDto;
import com.example.ECommerceBackend.exception.InvalidCustomerException;
import com.example.ECommerceBackend.exception.InvalidProductException;
import com.example.ECommerceBackend.model.*;
import com.example.ECommerceBackend.repository.CardRepository;
import com.example.ECommerceBackend.repository.CustomerRepository;
import com.example.ECommerceBackend.repository.OrderedRepository;
import com.example.ECommerceBackend.repository.ProductRepository;
import com.example.ECommerceBackend.service.OrderService;
import com.example.ECommerceBackend.service.ProductService;
import com.example.ECommerceBackend.transformer.ItemTransformer;
import com.example.ECommerceBackend.transformer.OrderTransformer;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    ProductService productService;
    @Autowired
     CustomerRepository customerRepository;
    @Autowired
     ProductRepository productRepository;
    @Autowired
     CardRepository cardRepository;
    @Autowired
     OrderedRepository orderedRepository;

    @Autowired
     JavaMailSender mailSender;

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

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception {
        Customer customer;
        try {
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        } catch (Exception e) {
            throw new InvalidCustomerException("Customer Id is Not Valid!!!");
        }

        Product product;
        try {
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        } catch (Exception e) {
            throw new InvalidProductException("Product id is not valid");
        }

        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());
        if (card.getCvv() != orderRequestDto.getCvv() || card == null || card.getCustomer() != customer) {
            throw new Exception("Card is not Valid!!!");
        }

        Item item = Item.builder()
                .requiredQuantity(orderRequestDto.getRequiredQuantity())
                .product(product)
                .build();

        try {
        productService.decreaseProductQuantity(item);
    }
        catch (Exception e) {
            throw new Exception(e);
        }


        Ordered order = new Ordered();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));

        String maskedCard = generateCardNo(card.getCardNo());

        order.setCardUsed(maskedCard);
        order.setCustomer(customer);
        order.setTotalValue(item.getRequiredQuantity() * product.getPrice());
        order.getItems().add(item);

        customer.getOrderList().add(order);
        item.setOrder(order);
        product.getItemList().add(item);

        Ordered savedOrder = orderedRepository.save(order);

        // Prepare Email
        String text = "Hi " + customer.getName() + ",\n" +
                "Thank you for shopping with " + "ECommerce Shop Marathahalli" + "!\n" +
                "Your Order of " + product.getName() + " has been placed with OrderId:"
                + order.getOrderNo()
                + "\n" + "It will be delivered with in 4 Days...";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("muralirgukt10@gmail.com");
        message.setTo(customer.getEmailId());
        message.setSubject("ECommerce Shop Marathahalli" + "Order Placed Successfully");
        message.setText(text);
        mailSender.send(message);

        OrderResponseDto orderResponseDto = OrderTransformer.OrderToOrderResponseDto(savedOrder);

        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        for(Item item1 : savedOrder.getItems()){
            ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(item1);
            itemResponseDtos.add(itemResponseDto);
        }
        orderResponseDto.setItems(itemResponseDtos);
        return orderResponseDto;
    }
}
