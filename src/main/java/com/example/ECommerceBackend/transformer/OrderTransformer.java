package com.example.ECommerceBackend.transformer;

import com.example.ECommerceBackend.dto.ResponseDto.DeleteOrderResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.OrderResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.OrderResponseDto2;
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

    public static OrderResponseDto2 OrderToOrderResponseDto2(Ordered ordered){
        return OrderResponseDto2.builder()
                .customerName(ordered.getCustomer().getName())
                .orderNo(ordered.getOrderNo())
                .noOfItems(ordered.getItems().size())
                .totalCost(ordered.getTotalValue())
                .orderDate(ordered.getOrderDate())
                .build();
    }

    public static DeleteOrderResponseDto deleteOrderResponseDto(Ordered ordered){
        return DeleteOrderResponseDto.builder()
                .id(ordered.getId())
                .orderNo(ordered.getOrderNo())
                .cardUsed(ordered.getCardUsed())
                .orderDate(ordered.getOrderDate())
                .message("Order Deleted Successfully!!")
                .build();
    }
}
