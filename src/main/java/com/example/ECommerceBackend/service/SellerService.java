package com.example.ECommerceBackend.service;

import com.example.ECommerceBackend.dto.RequestDto.SellerRequestDto;
import com.example.ECommerceBackend.dto.RequestDto.UpdateSellerRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.*;
import com.example.ECommerceBackend.exception.DuplicateSellerException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SellerService {
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws DuplicateSellerException;

    GetSellerResponseDto getSellerByEmail(String email) throws Exception;

    GetSellerResponseDto getSellerById(int id) throws Exception;

    List<GetSellerResponseDto> getAllSellers();

    UpdateSellerResponseDto updateSellerByEmailId(String emailId , UpdateSellerRequestDto updateSellerRequestDto) throws Exception;

    String deleteSellerByEmailId(String emailId) throws Exception;
    DeletedSellerResponseById deleteById(int id) throws Exception;

//    List<ProductResponseDto> getAllAvailableProducts() throws Exception;

}
