package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	 private Integer CustomerId; //顧客ID（PK）
	 private String	CustomerName; //氏名
	 private String CustomerEmail;//メールアドレス
}
