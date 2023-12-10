package com.example.ECommerceBackend.service;

import com.example.ECommerceBackend.dto.RequestDto.CardRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.CardResponseDto;
import com.example.ECommerceBackend.exception.InvalidCustomerException;
import com.example.ECommerceBackend.model.Card;
import org.springframework.stereotype.Service;

@Service
public interface CardService {

    CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException;
}
