package com.example.ECommerceBackend.service;

import com.example.ECommerceBackend.dto.RequestDto.ItemRequestDto;
import com.example.ECommerceBackend.model.Item;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {
    Item addItem(ItemRequestDto itemRequestDto) throws Exception;
}
