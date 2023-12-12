package com.example.ECommerceBackend.transformer;

import com.example.ECommerceBackend.dto.RequestDto.ItemRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.ItemResponseDto;
import com.example.ECommerceBackend.model.Item;

public class ItemTransformer {
    public static Item ItemRequestToItem(ItemRequestDto itemRequestDto){
        return Item.builder()
                .requiredQuantity(itemRequestDto.getRequiredQunatity())
                .build();
    }

    public static ItemResponseDto ItemToItemResponseDto(Item item){
//        String productName;
//        int priceOfOneItem;
//        int totalPrice;
//        int quantity;
        return ItemResponseDto.builder()
                .productName(item.getProduct().getName())
                .priceOfOneItem(item.getProduct().getPrice())
                .quantity(item.getRequiredQuantity())
                .totalPrice(item.getRequiredQuantity() * item.getProduct().getPrice())
                .build();
    }
}
