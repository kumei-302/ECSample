package com.example.demo.repository;
//作成者　粂井

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    /**
     * 商品名で検索
     */
    Optional<Product> findByName(String name);
    
    /**
     * 商品名に含まれる文字で検索
     */
    List<Product> findByNameContaining(String keyword);
    
    /**
     * 価格範囲で検索
     */
    List<Product> findByPriceBetween(Integer minPrice, Integer maxPrice);
    
    /**
     * 在庫があるかどうかで検索
     */
    List<Product> findByStockGreaterThan(Integer stock);
}