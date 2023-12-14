package com.example.ECommerceBackend.service;

import com.example.ECommerceBackend.dto.RequestDto.CheckOutCartRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.CartResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.OrderResponseDto;
import com.example.ECommerceBackend.model.Item;

public interface CartService {
    CartResponseDto saveCart(int customerId, Item item);

    OrderResponseDto checkOutCart(CheckOutCartRequestDto checkOutCartRequestDto) throws Exception;
}
