package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 作成者　粂井彩人
 * 顧客情報のエンティティクラス
 */
@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerId")
    private Integer customerId; //顧客ID（PK）
    
    @Column(name = "CustomerName")
    private String customerName; //氏名
    
    @Column(name = "CustomerEmail")
    private String customerEmail;//メールアドレス
    
    @Column(name = "CustomerAddress")
    private String customerAddress;//住所
}