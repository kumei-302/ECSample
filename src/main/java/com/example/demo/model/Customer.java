package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer CustomerId; //顧客ID（PK）
	 private String	CustomerName; //氏名
	 private String CustomerEmail;//メールアドレス
}
