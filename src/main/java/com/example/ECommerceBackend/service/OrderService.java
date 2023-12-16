package com.example.ECommerceBackend.service;

import com.example.ECommerceBackend.dto.RequestDto.OrderRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.OrderResponseDto;
import com.example.ECommerceBackend.model.Card;
import com.example.ECommerceBackend.model.Customer;
import com.example.ECommerceBackend.model.Ordered;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Ordered placeOrder(Customer customer, Card card) throws Exception;

    OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception;

}
