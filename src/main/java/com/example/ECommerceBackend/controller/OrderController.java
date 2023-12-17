package com.example.ECommerceBackend.controller;

import com.example.ECommerceBackend.dto.RequestDto.ItemRequestDto;
import com.example.ECommerceBackend.dto.RequestDto.OrderRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.DeleteOrderResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.OrderResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.OrderResponseDto2;
import com.example.ECommerceBackend.exception.InvalidCustomerException;
import com.example.ECommerceBackend.service.OrderService;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public OrderResponseDto placeDirectOrder(@RequestBody OrderRequestDto orderRequestDto) throws Exception {
        return orderService.placeOrder(orderRequestDto);
    }

    // Get All Orders List
    @GetMapping("/get/allOrders")
    public List<OrderResponseDto2> orders(@RequestParam("customerId") int customerId) throws InvalidCustomerException {
        try {
            return orderService.ordersList(customerId);
        }catch (Exception e){
            throw new InvalidCustomerException(e.getMessage());
        }
    }

    // Get Recent 5 Orders
    @GetMapping("/recent5orders")
    public List<OrderResponseDto2> getRecent5orders(@RequestParam("customerId") int customerId){
        return orderService.getRecent5orders(customerId);
    }

    // Delete Order
    @DeleteMapping("/deleteOrder")
    public DeleteOrderResponseDto deleteOrder(@RequestParam("id") int orderId) throws Exception {
        return orderService.deleteOrder(orderId);
    }
}
