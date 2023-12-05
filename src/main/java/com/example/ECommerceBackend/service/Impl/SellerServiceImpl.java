package com.example.ECommerceBackend.service.Impl;

import com.example.ECommerceBackend.dto.RequestDto.SellerRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.GetSellerResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.SellerResponseDto;
import com.example.ECommerceBackend.exception.DuplicateSellerException;
import com.example.ECommerceBackend.dto.RequestDto.model.Seller;
import com.example.ECommerceBackend.repository.SellerRepository;
import com.example.ECommerceBackend.service.SellerService;
import com.example.ECommerceBackend.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerRepository sellerRepository;

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws DuplicateSellerException{

        if(sellerRepository.findByEmailId(sellerRequestDto.getEmailId()) !=null) throw new DuplicateSellerException("Email id is already Rwgistered");

        Seller seller = SellerTransformer.SellerRequestToSeller(sellerRequestDto);

        Seller savedSeller = sellerRepository.save(seller);

        SellerResponseDto sellerResponseDto =SellerTransformer.sellerToSellerResponseDto(savedSeller);

        return sellerResponseDto;

    }

    @Override
    public GetSellerResponseDto getSellerByEmail(String emailId) throws Exception{
        Seller seller;
        try{
            seller = sellerRepository.findByEmailId(emailId);
        }
        catch (Exception e){
            throw new Exception("Email Id is not registered");
        }

        GetSellerResponseDto getSellerResponseDto = SellerTransformer.SellerObjToGetSellerResponse(seller);
        return getSellerResponseDto;
    }
}
