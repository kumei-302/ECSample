package com.example.demo.service;
//作成者　粂井

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;

@Service
public class ProductService {

    @Autowired
    private com.example.demo.repository.jpa.ProductRepository productRepository;

    //商品を保存する
    public Product save(Product product) {
        return productRepository.save(product);
    }
    
    //商品名で重複チェック
    public boolean existsByProductName(String ProductName) {
        return productRepository.existsByProductName(ProductName);
    }

    //全商品を取得
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    //IDで商品を取得
    public Optional<Product> findByProductId(Integer ProductId) {
        return productRepository.findById(ProductId);
    }

    //商品を削除
    public void deleteByProductId(Integer ProductId) {
        productRepository.deleteById(ProductId);
    }

    //商品名で検索
    public List<Product> searchByProductName(String keyword) {
        return productRepository.findByProductNameContaining(keyword);
    }
    
    //価格範囲で検索
    public List<Product> findByPriceRange(Integer minPrice, Integer maxPrice) {
        return productRepository.findByProductPriceBetween(minPrice, maxPrice);
    }
    
    //在庫があるかどうかで検索
    public List<Product> findByStockGreaterThan(Integer ProductStock) {
        return productRepository.findByProductStockGreaterThan(ProductStock);
    }
}