package com.example.ECommerceBackend.service.Impl;

import com.example.ECommerceBackend.Enum.CardType;
import com.example.ECommerceBackend.dto.RequestDto.CardRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.CardResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.CardResponseDto2;
import com.example.ECommerceBackend.exception.InvalidCustomerException;
import com.example.ECommerceBackend.exception.InvalidSellerException;
import com.example.ECommerceBackend.model.Card;
import com.example.ECommerceBackend.model.Customer;
import com.example.ECommerceBackend.repository.CardRepository;
import com.example.ECommerceBackend.repository.CustomerRepository;
import com.example.ECommerceBackend.service.CardService;
import com.example.ECommerceBackend.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException {
        Customer customer = customerRepository.findByMobile(cardRequestDto.getMobile());
        if(customer==null){
            throw new InvalidCustomerException("Sorry! No customer exist with this Mobile number");
        }

        Card card = CardTransformer.cardRequestDtoToCard(cardRequestDto);

        // card stores about customer
        card.setCustomer(customer);

        // customer adds this card to his list
        customer.getCards().add(card);

        // Saves both customer and card
        customerRepository.save(customer);

        return CardTransformer.CardToCardResponseDto(card);
    }

    public List<CardResponseDto2> getVisaCards(){
        List<Card> cardList = cardRepository.findAll();
        List<CardResponseDto2> cardResponseDto2List = new ArrayList<>();
        for(Card card : cardList){
            if(card.getCardType() == CardType.VISA){
                cardResponseDto2List.add(CardTransformer.CardToCardResponseDto2(card));
            }
        }
        return cardResponseDto2List;
    }

    public List<CardResponseDto2> getCardsWithExpiryDateGreaterThanGivenDate(Date expiryDate){
        List<Card> cards = cardRepository.getCardsWithExpiryDateGreaterThanGivenDate(expiryDate);
        List<CardResponseDto2> cardsResult = new ArrayList<>();
        for(Card card : cards){
            cardsResult.add(CardTransformer.CardToCardResponseDto2(card));
        }

        return cardsResult;
    }

    public List<CardResponseDto2>getMasterCardsWithExpiryDateGreaterThan2k25(){
        List<Card> cards = cardRepository.getMasterCardsWithExpiryDateGreaterThan2k25();
        List<CardResponseDto2> result = new ArrayList<>();
        for(Card card : cards){
            if(card.getCardType()==CardType.MASTERCARD){
                result.add(CardTransformer.CardToCardResponseDto2(card));
            }
        }
        return result;
    }
}
