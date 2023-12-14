package com.example.ECommerceBackend.service;

import com.example.ECommerceBackend.dto.RequestDto.ItemRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.OrderResponseDto;
import com.example.ECommerceBackend.model.Card;
import com.example.ECommerceBackend.model.Customer;
import com.example.ECommerceBackend.model.Item;
import com.example.ECommerceBackend.model.Ordered;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {
    Item addItem(ItemRequestDto itemRequestDto) throws Exception;


}
