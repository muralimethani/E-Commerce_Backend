package com.example.ECommerceBackend.transformer;

import com.example.ECommerceBackend.dto.RequestDto.CustomerRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.CustomerResponseDTO2;
import com.example.ECommerceBackend.dto.ResponseDto.CustomerResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.DeleteCustomerDto;
import com.example.ECommerceBackend.dto.ResponseDto.UpdatedCustomerResponseDto;
import com.example.ECommerceBackend.model.Customer;

public class CustomerTransformer {
    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .address(customerRequestDto.getAddress())
                .emailId(customerRequestDto.getEmailId())
                .mobile(customerRequestDto.getMobile())
                .build();
    }

    public static CustomerResponseDto customerToCustomerResponseDto(Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .mobile(customer.getMobile())
                .message("Hi!" + customer.getName() +" Welcome TO Ecommerce!!").build();
    }

    public static CustomerResponseDTO2 customerToCustomerResponseDtos(Customer customer){
        return CustomerResponseDTO2.builder()
                .name(customer.getName())
                .address(customer.getAddress())
                .mobile(customer.getMobile())
                .address(customer.getAddress())
                .emailId(customer.getEmailId())
                .build();
    }

    public static DeleteCustomerDto deleteCustomerDto(Customer customer){
        return DeleteCustomerDto.builder()
                .name(customer.getName())
                .mobile(customer.getMobile())
                .message("Customer with Mobie Number " + customer.getMobile() + " deleted Successfully")
                .build();
    }

    public static UpdatedCustomerResponseDto customerToUpdateCustomerResponseDto(Customer customer){
        return UpdatedCustomerResponseDto.builder()
                .name(customer.getName())
                .age(customer.getAge())
                .emailId(customer.getEmailId())
                .mobile(customer.getMobile())
                .message("Details Updated Successfully :)")
                .build();
    }
}
