package com.example.ECommerceBackend.service;

import com.example.ECommerceBackend.dto.RequestDto.CardRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.CardResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.CardResponseDto2;
import com.example.ECommerceBackend.exception.InvalidCustomerException;
import com.example.ECommerceBackend.model.Card;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface CardService {

    CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException;

    List<CardResponseDto2> getVisaCards();

    List<CardResponseDto2> getCardsWithExpiryDateGreaterThanGivenDate(Date expiryDate);

    List<CardResponseDto2>getMasterCardsWithExpiryDateGreaterThan2k25();
}
