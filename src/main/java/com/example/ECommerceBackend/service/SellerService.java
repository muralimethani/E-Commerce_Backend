package com.example.ECommerceBackend.service;

import com.example.ECommerceBackend.dto.RequestDto.SellerRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.GetSellerResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.SellerResponseDto;
import com.example.ECommerceBackend.exception.DuplicateSellerException;
import org.springframework.stereotype.Service;

@Service
public interface SellerService {
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws DuplicateSellerException;

    GetSellerResponseDto getSellerByEmail(String email) throws Exception;


}
