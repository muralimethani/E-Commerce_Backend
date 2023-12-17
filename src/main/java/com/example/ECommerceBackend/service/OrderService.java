package com.example.ECommerceBackend.service;

import com.example.ECommerceBackend.dto.RequestDto.OrderRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.DeleteOrderResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.OrderResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.OrderResponseDto2;
import com.example.ECommerceBackend.exception.InvalidCustomerException;
import com.example.ECommerceBackend.model.Card;
import com.example.ECommerceBackend.model.Customer;
import com.example.ECommerceBackend.model.Ordered;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public interface OrderService {
    Ordered placeOrder(Customer customer, Card card) throws Exception;

    OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception;

    List<OrderResponseDto2> ordersList(int customerId) throws InvalidCustomerException;

    List<OrderResponseDto2> getRecent5orders(int customerId);

    DeleteOrderResponseDto deleteOrder(int id) throws Exception;

}
