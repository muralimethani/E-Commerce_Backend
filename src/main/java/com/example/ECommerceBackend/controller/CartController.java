package com.example.ECommerceBackend.controller;

import com.example.ECommerceBackend.dto.RequestDto.CheckOutCartRequestDto;
import com.example.ECommerceBackend.dto.RequestDto.ItemRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.CartItemResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.CartResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.DeleteCartResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.OrderResponseDto;
import com.example.ECommerceBackend.model.Item;
import com.example.ECommerceBackend.service.CartService;
import com.example.ECommerceBackend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/checkout")
    public OrderResponseDto checkOutCart(@RequestBody CheckOutCartRequestDto checkOutCartRequestDto) throws Exception{
        return cartService.checkOutCart(checkOutCartRequestDto);
    }


    // Get Items in Cart
    @GetMapping("/itemsInCart")
    public List<CartItemResponseDto> itemsInCart(){
        return cartService.itemsInCart();
    }

    // Delete Cart
    @DeleteMapping("/deleteCart")
    public DeleteCartResponseDto deleteCart(@RequestParam("id") int id) throws Exception {
        return cartService.deleteCart(id);
    }
}
