package com.example.ECommerceBackend.controller;

import com.example.ECommerceBackend.dto.RequestDto.CardRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.CardResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.CardResponseDto2;
import com.example.ECommerceBackend.exception.InvalidCustomerException;
import com.example.ECommerceBackend.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
//import java.sql.Date;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    // Add Card
    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto) throws InvalidCustomerException {
        try {
            CardResponseDto cardResponseDto = cardService.addCard(cardRequestDto);
            return new ResponseEntity(cardResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return  new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Get All Visa Cards
    @GetMapping("/getVisaCards")
    public List<CardResponseDto2> getVisaCards(){
        return cardService.getVisaCards();
    }

    // get all  cards whose expiry date greater than given date
    @GetMapping("/getCardsWithExpiryDateGreaterThanGivenDate")
    public List<CardResponseDto2> getCardsWithExpiryDateGreaterThanGivenDate(@RequestParam("expiryDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date expiryDate){
        return cardService.getCardsWithExpiryDateGreaterThanGivenDate(expiryDate);
    }

    // get all MASTER CARDS whose expiry date greater than jan 2k25
    @GetMapping("/getMasterCardsWithGivenDate")
    public List<CardResponseDto2> getMasterCardsWithExpiryDateGreaterThan2k25(){
        return cardService.getMasterCardsWithExpiryDateGreaterThan2k25();
    }


}
