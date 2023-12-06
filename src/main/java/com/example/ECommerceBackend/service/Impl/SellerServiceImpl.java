package com.example.ECommerceBackend.service.Impl;

import com.example.ECommerceBackend.dto.RequestDto.SellerRequestDto;
import com.example.ECommerceBackend.dto.RequestDto.UpdateSellerRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.DeletedSellerResponseById;
import com.example.ECommerceBackend.dto.ResponseDto.GetSellerResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.SellerResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.UpdateSellerResponseDto;
import com.example.ECommerceBackend.exception.DuplicateSellerException;
import com.example.ECommerceBackend.model.Seller;
import com.example.ECommerceBackend.repository.SellerRepository;
import com.example.ECommerceBackend.service.SellerService;
import com.example.ECommerceBackend.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public GetSellerResponseDto getSellerById(int id) throws Exception{
        Seller seller;
        try{
            seller = sellerRepository.findById(id).get();
        }
        catch (Exception e){
            throw new Exception("Seller Id is not present");
        }

        GetSellerResponseDto getSellerResponseDto = SellerTransformer.SellerObjToGetSellerResponse(seller);
        return getSellerResponseDto;
    }

    public List<GetSellerResponseDto> getAllSellers(){
        List<Seller> sellers = sellerRepository.findAll();
        List<GetSellerResponseDto> sellerResponseDtoList = new ArrayList<>();
        for(Seller seller : sellers){
            GetSellerResponseDto getSellerResponseDto = new GetSellerResponseDto();
            getSellerResponseDto.setName(seller.getName());
            getSellerResponseDto.setId(seller.getId());
            getSellerResponseDto.setMobNo(seller.getMobNo());
            getSellerResponseDto.setEmailId(seller.getEmailId());
            getSellerResponseDto.setAge(seller.getAge());

            sellerResponseDtoList.add(getSellerResponseDto);

        }
        return sellerResponseDtoList;
    }

    public UpdateSellerResponseDto updateSellerByEmailId(String emailId, UpdateSellerRequestDto updateSellerRequestDto) throws Exception{
        Seller seller;
        try{
            seller = sellerRepository.findByEmailId(emailId);
        }
        catch (Exception e){
            throw new Exception("Email Id is not Valid");
        }
        seller.setMobNo(updateSellerRequestDto.getMobNo());
        seller.setAge(updateSellerRequestDto.getAge());

        Seller updatedSeller = sellerRepository.save(seller);

        UpdateSellerResponseDto updateSellerResponseDto = SellerTransformer.updateTOSellerResponseDto(updatedSeller);
        return updateSellerResponseDto;
    }

    public String deleteSellerByEmailId(String emailId) throws Exception{
        Seller seller;
        try{
            seller = sellerRepository.findByEmailId(emailId);
        }
        catch (Exception e){
            throw new Exception("There is no user exist with given EmailId");
        }
        sellerRepository.delete(seller);
        return "Seller Deleted Successfully";
    }

    public DeletedSellerResponseById deleteById(int id) throws Exception{
        Seller seller;
        try{
            seller = sellerRepository.findById(id).get();
        }
        catch(Exception e){
            throw new Exception("Seller id is not valid");
        }
        sellerRepository.deleteById(id);
        return SellerTransformer.getSeller(seller);
    }
}
