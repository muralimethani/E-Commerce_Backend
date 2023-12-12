package com.example.ECommerceBackend.service;

import com.example.ECommerceBackend.dto.ResponseDto.CartResponseDto;
import com.example.ECommerceBackend.model.Item;

public interface CartService {
    CartResponseDto saveCart(int customerId, Item item);
}
