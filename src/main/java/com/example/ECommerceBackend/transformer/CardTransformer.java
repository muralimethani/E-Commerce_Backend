package com.example.ECommerceBackend.transformer;

import com.example.ECommerceBackend.dto.RequestDto.CardRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.CardResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.CardResponseDto2;
import com.example.ECommerceBackend.model.Card;

public class CardTransformer {

    public  static Card cardRequestDtoToCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .expiryDate(cardRequestDto.getExpiryDate())
                .build();
    }

    public  static CardResponseDto CardToCardResponseDto(Card card){
        return CardResponseDto.builder()
                .cardNo(card.getCardNo())
                .customerName(card.getCustomer().getName())
                .message("Card has been Added Successfully!")
                .build();
    }

    public static CardResponseDto2 CardToCardResponseDto2(Card card){
        return CardResponseDto2.builder()
                .customerName(card.getCustomer().getName())
                .cardNo(card.getCardNo())
                .cvv(card.getCvv())
                .expiryDate(card.getExpiryDate())
                .cardType(card.getCardType()).build();
    }
}
