package com.example.ECommerceBackend.service.Impl;

import com.example.ECommerceBackend.Enum.CardType;
import com.example.ECommerceBackend.dto.RequestDto.CustomerRequestDto;
import com.example.ECommerceBackend.dto.RequestDto.UpdateCustomerRequestDto;
import com.example.ECommerceBackend.dto.ResponseDto.CustomerResponseDTO2;
import com.example.ECommerceBackend.dto.ResponseDto.CustomerResponseDto;
import com.example.ECommerceBackend.dto.ResponseDto.DeleteCustomerDto;
import com.example.ECommerceBackend.dto.ResponseDto.UpdatedCustomerResponseDto;
import com.example.ECommerceBackend.exception.InvalidCustomerException;
import com.example.ECommerceBackend.model.Card;
import com.example.ECommerceBackend.model.Cart;
import com.example.ECommerceBackend.model.Customer;
import com.example.ECommerceBackend.repository.CardRepository;
import com.example.ECommerceBackend.repository.CustomerRepository;
import com.example.ECommerceBackend.service.CustomerService;
import com.example.ECommerceBackend.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private CardRepository cardRepository;

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws InvalidCustomerException {
        if(customerRepository.findByMobile(customerRequestDto.getMobile())!=null){
            throw new InvalidCustomerException("Sorry, Customer already presnt with this Mobile Number:");
        }
        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);

        Cart cart = Cart.builder()
                .totalCost(0)
                .numberOfItems(0)
                .customer(customer)
                .build();

        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer);

        return CustomerTransformer.customerToCustomerResponseDto(savedCustomer);

    }

    public List<CustomerResponseDTO2> getAllCustomers(){
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponseDTO2> customerResponseDTO2s = new ArrayList<>();
        for(Customer c : customers){
            customerResponseDTO2s.add(CustomerTransformer.customerToCustomerResponseDtos(c));
        }

        return customerResponseDTO2s;
    }

    public CustomerResponseDTO2 getByEmailId(String emailId) throws Exception{
        Customer customer;
        try {
            customer = customerRepository.findByEmailId(emailId);
        }
        catch (Exception e){
            throw new InvalidCustomerException("Customer is Not Exist with " + emailId + " Id");
        }
        return CustomerTransformer.customerToCustomerResponseDtos(customer);
    }

    public List<CustomerResponseDTO2> getCustomersWithVisaCards(){
        List<Card> cards = cardRepository.findAll();
        List<Customer> customers = new ArrayList<>();
        for(Card card : cards){
            if(card.getCardType() == CardType.VISA){
                customers.add(card.getCustomer());
            }
        }

        List<CustomerResponseDTO2> customerResponseDTO2s = new ArrayList<>();
        for(Customer customer : customers){
            customerResponseDTO2s.add(CustomerTransformer.customerToCustomerResponseDtos(customer));
        }
        return  customerResponseDTO2s;
    }

    public List<CustomerResponseDTO2> customersWithGreaterThanGiven(int age){
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponseDTO2> customerResponseDTO2s = new ArrayList<>();
        for(Customer customer : customers){
            if(customer.getAge() > age){
                customerResponseDTO2s.add(CustomerTransformer.customerToCustomerResponseDtos(customer));
            }
        }
        return customerResponseDTO2s;
    }

    public DeleteCustomerDto deleteByMobile(String mobile) throws Exception{
        Customer customer;
        try {
            customer = customerRepository.findByMobile(mobile);
        }catch (Exception e){
            throw new InvalidCustomerException("Customer is Not Exist with this Mobile Number");
        }
        customerRepository.delete(customer);
        return CustomerTransformer.deleteCustomerDto(customer);
    }

    public UpdatedCustomerResponseDto updateByEmailId(String emailId, UpdateCustomerRequestDto updateCustomerRequestDto){
        Customer customer = customerRepository.findByEmailId(emailId);

        customer.setMobile(updateCustomerRequestDto.getMobile());
        customer.setAge(updateCustomerRequestDto.getAge());
        Customer updated = customerRepository.save(customer);

        UpdatedCustomerResponseDto updatedCustomerResponseDto = CustomerTransformer.customerToUpdateCustomerResponseDto(updated);
        return updatedCustomerResponseDto;
    }
}
