package com.example.demo.repository.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Customer;

/*
 * 作成者　粂井　顧客管理のrepositoryインターフェイス　今のところ重複管理機能のみ
 */

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByCustomerEmail(String customerEmail); // 重複確認などで使う
}