package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.model.CustomerForm;
import com.example.demo.repository.CustomerRepository;

//作成者　粂井

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    //ユーザー登録
    public Customer register(CustomerForm form) {
        Customer customer = new Customer();
        customer.setCustomerName(form.getName());
        customer.setCustomerEmail(form.getEmail());
        return customerRepository.save(customer);
    }
}