package com.example.ECommerceBackend.service;

import com.example.ECommerceBackend.dto.RequestDto.SellerRequestDto;
import com.example.ECommerceBackend.dto.RequestDto.UpdateSellerRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.DeletedSellerResponseById;
import com.example.ECommerceBackend.dto.ResponseDto.GetSellerResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.SellerResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.UpdateSellerResponseDto;
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

}
