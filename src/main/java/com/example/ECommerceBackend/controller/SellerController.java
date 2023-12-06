package com.example.ECommerceBackend.controller;

import com.example.ECommerceBackend.dto.RequestDto.SellerRequestDto;
import com.example.ECommerceBackend.dto.RequestDto.UpdateSellerRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.DeletedSellerResponseById;
import com.example.ECommerceBackend.dto.ResponseDto.GetSellerResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.SellerResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.UpdateSellerResponseDto;
import com.example.ECommerceBackend.exception.DuplicateSellerException;
import com.example.ECommerceBackend.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    // Add Seller
    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto) throws DuplicateSellerException {
        try{
            SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);
            return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Get Seller By Email Id
    @GetMapping("/get_by_emailId")
    public ResponseEntity getSellerByEmailId(@RequestParam("emailId") String emailId) throws Exception{
        try {
            GetSellerResponseDto getSellerResponseDto = sellerService.getSellerByEmail(emailId);
            return new ResponseEntity(getSellerResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Get seller by Id
    @GetMapping("/get_by_id")
    public ResponseEntity getSellerById(@RequestParam("id") int id) throws Exception{
        try{
            GetSellerResponseDto getSellerResponseDto = sellerService.getSellerById(id);
            return new ResponseEntity(getSellerResponseDto, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Get List of sellers
    @GetMapping("/get_all")
    public List<GetSellerResponseDto> getAllSellers(){
        return sellerService.getAllSellers();
    }

    // Update Seller By EmailId
    @PutMapping ("/update_byEmailId")
    public ResponseEntity updateSellerByEmailId(@RequestParam("emailId") String emailId, @RequestBody UpdateSellerRequestDto updateSellerRequestDto) throws Exception{
        try{
            UpdateSellerResponseDto updateSellerResponseDto = sellerService.updateSellerByEmailId(emailId, updateSellerRequestDto);
            return new ResponseEntity(updateSellerResponseDto,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // delete by MailId
    @DeleteMapping("/delete_byEmailId")
    public String deleteSellerByEmailId(@RequestParam("emailId") String emailId) throws Exception{
        try{
             return sellerService.deleteSellerByEmailId(emailId);
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    // Delete by Id
    @DeleteMapping("/delete_byId")
    public ResponseEntity deleteById(@RequestParam("id") int id) throws Exception{
        try{
            DeletedSellerResponseById deletedSellerResponseById = sellerService.deleteById(id);
            return new ResponseEntity(deletedSellerResponseById, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
