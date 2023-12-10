package com.example.ECommerceBackend.service;

import com.example.ECommerceBackend.controller.CustomerController;
import com.example.ECommerceBackend.dto.RequestDto.CustomerRequestDto;
import com.example.ECommerceBackend.dto.RequestDto.UpdateCustomerRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.CustomerResponseDTO2;
import com.example.ECommerceBackend.dto.ResponseDto.CustomerResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.DeleteCustomerDto;
import com.example.ECommerceBackend.dto.ResponseDto.UpdatedCustomerResponseDto;
import com.example.ECommerceBackend.exception.InvalidCustomerException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws InvalidCustomerException;

    List<CustomerResponseDTO2> getAllCustomers();

    CustomerResponseDTO2 getByEmailId(String emailId) throws Exception;

    List<CustomerResponseDTO2> getCustomersWithVisaCards();

    List<CustomerResponseDTO2> customersWithGreaterThanGiven(int age);

    DeleteCustomerDto deleteByMobile(String mobile) throws Exception;

    UpdatedCustomerResponseDto updateByEmailId(String emailId, UpdateCustomerRequestDto updateCustomerRequestDto);
}
