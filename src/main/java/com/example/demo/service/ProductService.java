package com.example.demo.service;
//作成者　粂井

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * 商品を保存する
     */
    public Product save(Product product) {
        return productRepository.save(product);
    }

    /**
     * 商品名で重複チェック
     */
    public boolean existsByName(String name) {
        return productRepository.findByName(name).isPresent();
    }

    /**
     * 全商品を取得
     */
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    /**
     * IDで商品を取得
     */
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * 商品を削除
     */
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * 商品名で検索
     */
    public List<Product> searchByName(String keyword) {
        return productRepository.findByNameContaining(keyword);
    }
}