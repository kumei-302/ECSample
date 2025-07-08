
package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.EmailAlreadyExistsException;
import com.example.demo.form.CustomerForm;
import com.example.demo.model.Customer;
import com.example.demo.repository.jpa.CustomerRepository;

//作成者　粂井

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    /**
     * 顧客登録処理
     * @param form 顧客フォーム
     * @return 登録された顧客情報
     * @throws EmailAlreadyExistsException メールアドレスが既に存在する場合
     */
    public Customer register(CustomerForm form) throws EmailAlreadyExistsException {
        // メールアドレスの重複チェック
        if (isEmailExists(form.getCustomerEmail())) {
            throw new EmailAlreadyExistsException("このメールアドレスは既に登録されています: " + form.getCustomerEmail());
        }

        Customer customer = new Customer();
        customer.setCustomerName(form.getCustomerName());
        customer.setCustomerEmail(form.getCustomerEmail());
        customer.setCustomerAddress(form.getCustomerAddress());
        return customerRepository.save(customer);
    }

    /**
     * メールアドレスの重複チェック
     * @param email チェック対象のメールアドレス
     * @return 存在する場合はtrue、存在しない場合はfalse
     */
    public boolean isEmailExists(String email) {
        Optional<Customer> existingCustomer = customerRepository.findByCustomerEmail(email);
        return existingCustomer.isPresent();
    }
}