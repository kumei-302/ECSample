package com.example.demo.model;
/*
 * 作成者粂井商品情報のエンティティクラス
 */
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductId")
    private Integer productId;

    @Column(name = "CategoryId")
    private Integer categoryId;

    @Column(name = "ProductName")
    private String productName;

    @Column(name = "ProductPrice")
    private Integer productPrice;

    @Column(name = "ProductStock")
    private Integer productStock;
}
