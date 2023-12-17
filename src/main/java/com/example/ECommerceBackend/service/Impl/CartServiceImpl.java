package com.example.ECommerceBackend.service.Impl;

import com.example.ECommerceBackend.dto.RequestDto.CheckOutCartRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.*;
import com.example.ECommerceBackend.exception.InvalidCustomerException;
import com.example.ECommerceBackend.model.*;
import com.example.ECommerceBackend.repository.*;
import com.example.ECommerceBackend.service.CartService;
import com.example.ECommerceBackend.service.OrderService;
import com.example.ECommerceBackend.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    CustomerRepository customerRepository;

//    @Autowired
//    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderedRepository orderedRepository;

    public CartResponseDto saveCart(int customerId, Item item){
//        int cartTotal;
//        String customerName;
//        int noOfItems;
//        List<ItemResponseDto> items;

        Customer customer = customerRepository.findById(customerId).get();
        Cart cart = customer.getCart();

        int newTotal = cart.getTotalCost() + item.getProduct().getPrice() * item.getRequiredQuantity();
        cart.setTotalCost(newTotal);
        cart.getItems().add(item);
        cart.setNumberOfItems(cart.getItems().size());
        Cart savedCart = cartRepository.save(cart);

        CartResponseDto cartResponseDto = CartResponseDto.builder()
                .customerName(customer.getName())
                .noOfItems(cart.getNumberOfItems())
                .cartTotal(cart.getTotalCost())
                .build();

        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        for(Item item1 : cart.getItems()){
            itemResponseDtos.add(ItemTransformer.ItemToItemResponseDto(item1));
        }

        cartResponseDto.setItems(itemResponseDtos);
        return cartResponseDto;
    }

    public OrderResponseDto checkOutCart(CheckOutCartRequestDto checkOutCartRequestDto) throws Exception {
        Customer customer;
        try {
            customer = customerRepository.findById(checkOutCartRequestDto.getCustomerId()).get();
        }catch (Exception e){
            throw new InvalidCustomerException("Customer Don't exist with given Id!!!");
        }

        Card card = cardRepository.findById(checkOutCartRequestDto.getCustomerId()).get();
        if(card == null || card.getCvv() != checkOutCartRequestDto.getCvv() || card.getCustomer() != customer){
            throw new Exception("Invalid Card!!!");
        }

        Cart cart = customer.getCart();
        if(cart.getNumberOfItems() == 0){
            throw new Exception("Cart is Empty!!");
        }

        try {
            Ordered order = orderService.placeOrder(customer, card);
            customer.getOrderList().add(order);
            resetCart(cart);

            Ordered savedOrder = orderedRepository.save(order);

            // prepare response dto
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setOrderDate(savedOrder.getOrderDate());
            orderResponseDto.setOrderNo(savedOrder.getOrderNo());
            orderResponseDto.setCustomerName(customer.getName());
            orderResponseDto.setTotalValue(savedOrder.getTotalValue());
            orderResponseDto.setCardUsed(savedOrder.getCardUsed());

            List<ItemResponseDto> items = new ArrayList<>();
            for(Item  item : savedOrder.getItems()){
                ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(item);
                items.add(itemResponseDto);
            }
            orderResponseDto.setItems(items);
            return orderResponseDto;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<CartItemResponseDto> itemsInCart(){
        List<Customer>customers = customerRepository.findAll();
        List<CartItemResponseDto> cartItemResponseDtoList = new ArrayList<>();

        for(Customer customer : customers){
            CartItemResponseDto cartItemsResponseDto = new CartItemResponseDto();
            List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
            List<Item> items = customer.getCart().getItems();

            for(Item item : items){
                ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(item);
                itemResponseDtos.add(itemResponseDto);
            }
            cartItemsResponseDto.setCustomerName(customer.getName());
            cartItemsResponseDto.setList(itemResponseDtos);
            cartItemResponseDtoList.add(cartItemsResponseDto);
        }
        return cartItemResponseDtoList;
    }

    public DeleteCartResponseDto deleteCart(int id) throws Exception {
        Cart cart = null;
        try {
            cart = cartRepository.findById(id).get();
        }catch (Exception e){
            throw new Exception("Cart is not exist");
        }
//        if(cart.getItems().size()==0){
//
//        }
        cartRepository.delete(cart);
        DeleteCartResponseDto deleteCartResponseDto = new DeleteCartResponseDto();
        deleteCartResponseDto.setCustomerName(cart.getCustomer().getName());
        deleteCartResponseDto.setNoOfItems(cart.getNumberOfItems());
        deleteCartResponseDto.setCartTotal(cart.getTotalCost());
        List<Item> items = cart.getItems();
        List<ItemResponseDto> list = new ArrayList<>();
        for(Item item : items){
            ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(item);
            list.add(itemResponseDto);
        }
        deleteCartResponseDto.setList(list);

        return deleteCartResponseDto;
    }
    public void resetCart(Cart cart){
        cart.setItems(new ArrayList<>());
        cart.setNumberOfItems(0);
        cart.setTotalCost(0);
    }
}
