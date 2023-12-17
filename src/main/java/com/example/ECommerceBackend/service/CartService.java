package com.example.ECommerceBackend.service;

import com.example.ECommerceBackend.dto.RequestDto.CheckOutCartRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.CartItemResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.CartResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.DeleteCartResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.OrderResponseDto;
import com.example.ECommerceBackend.model.Item;

import java.util.List;

public interface CartService {
    CartResponseDto saveCart(int customerId, Item item);

    OrderResponseDto checkOutCart(CheckOutCartRequestDto checkOutCartRequestDto) throws Exception;

    List<CartItemResponseDto> itemsInCart();

    DeleteCartResponseDto deleteCart(int id) throws Exception;
}
