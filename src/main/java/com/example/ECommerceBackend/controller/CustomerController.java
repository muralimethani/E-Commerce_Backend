package com.example.ECommerceBackend.controller;

import com.example.ECommerceBackend.dto.RequestDto.CustomerRequestDto;
import com.example.ECommerceBackend.dto.RequestDto.UpdateCustomerRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.CustomerResponseDTO2;
import com.example.ECommerceBackend.dto.ResponseDto.CustomerResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.DeleteCustomerDto;
import com.example.ECommerceBackend.dto.ResponseDto.UpdatedCustomerResponseDto;
import com.example.ECommerceBackend.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws Exception {
        try {
            CustomerResponseDto customerResponseDto = customerService.addCustomer(customerRequestDto);
            return new ResponseEntity(customerResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Get ALl cutomers
    @GetMapping("/getAll")
    public List<CustomerResponseDTO2> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    // Get Customer By emailId
    @GetMapping("/get/byEmailId")
    public ResponseEntity getByEmailId(@RequestParam String emailId){
        try {
            CustomerResponseDTO2 customerResponseDTO2 = customerService.getByEmailId(emailId);
            return new ResponseEntity(customerResponseDTO2, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Get Customers of Visa Card Types
    @GetMapping("/getCustomersOfVisaCards")
    public List<CustomerResponseDTO2> getCustomersWithVisaCards(){
        return customerService.getCustomersWithVisaCards();
    }

    // Get Customer > age
    @GetMapping("/getByAge")
    public List<CustomerResponseDTO2> customersWithGreaterThanGiven(int age){
        return customerService.customersWithGreaterThanGiven(age);
    }

    // Delete Customer By MobileNo
    @DeleteMapping("/deleteByMobileNo")
    public DeleteCustomerDto deleteByMobile(String mobile) throws Exception{
        return customerService.deleteByMobile(mobile);
    }

    // Update customer by email
    @PutMapping("/updateByEmailId")
    public UpdatedCustomerResponseDto updateByEmailId(@RequestParam ("emailId") String emailId, @RequestBody UpdateCustomerRequestDto updateCustomerRequestDto){
        return customerService.updateByEmailId(emailId, updateCustomerRequestDto);
    }
}
