package com.example.ECommerceBackend.transformer;

import com.example.ECommerceBackend.dto.RequestDto.SellerRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.GetSellerResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.SellerResponseDto;
import com.example.ECommerceBackend.dto.RequestDto.model.Seller;

public class SellerTransformer {

    public static Seller SellerRequestToSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .age(sellerRequestDto.getAge())
                .emailId(sellerRequestDto.getEmailId())
                .mobNo(sellerRequestDto.getMobNo())
                .build();
    }

    public static SellerResponseDto sellerToSellerResponseDto(Seller seller){
        return SellerResponseDto.builder()
                .name(seller.getName())
                .age(seller.getAge())
//                .status("Seller Added Successfully")
                .build();
    }

    public static GetSellerResponseDto SellerObjToGetSellerResponse(Seller seller){
        return GetSellerResponseDto.builder()
                .id(seller.getId())
                .age(seller.getAge())
                .mobNo(seller.getMobNo())
                .emailId(seller.getEmailId())
                .name(seller.getName()).build();
    }
}
