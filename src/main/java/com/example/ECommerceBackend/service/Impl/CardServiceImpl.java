package com.example.ECommerceBackend.service.Impl;

import com.example.ECommerceBackend.dto.RequestDto.CardRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.CardResponseDto;
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
}
