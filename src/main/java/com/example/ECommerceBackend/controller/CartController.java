package com.example.ECommerceBackend.controller;

import com.example.ECommerceBackend.dto.RequestDto.ItemRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.CartResponseDto;
import com.example.ECommerceBackend.model.Item;
import com.example.ECommerceBackend.service.CartService;
import com.example.ECommerceBackend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemService itemService;
    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto){
        try {
            Item savedItem =itemService.addItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.saveCart(itemRequestDto.getCustomerId(), savedItem);
            return new ResponseEntity<>(cartResponseDto, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
