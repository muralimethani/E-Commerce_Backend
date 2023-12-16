package com.example.ECommerceBackend.transformer;

import com.example.ECommerceBackend.dto.ResponseDto.OrderResponseDto;
import com.example.ECommerceBackend.model.Ordered;

public class OrderTransformer {
    public static OrderResponseDto OrderToOrderResponseDto(Ordered ordered){
        return OrderResponseDto.builder()
                .orderDate(ordered.getOrderDate())
                .orderNo(ordered.getOrderNo())
                .cardUsed(ordered.getCardUsed())
                .totalValue(ordered.getTotalValue())
                .customerName(ordered.getCustomer().getName())
                .build();
    }
}
