package com.example.demo.repository.jpa;
/*作成者　粂井
* 商品の検索などのrepositoryインターフェイス
*/
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    // 商品名で検索
    Optional<Product> findByProductName(String ProductName);
    
    // 商品名に含まれる文字で検索
    List<Product> findByProductNameContaining(String keyword);
    
    // 価格範囲で検索
    List<Product> findByProductPriceBetween(Integer minPrice, Integer maxPrice);
    
    // 在庫があるかどうかで検索（修正：ProductStockに合わせて修正）
    List<Product> findByProductStockGreaterThan(Integer ProductStock);
    
    // 商品名の存在チェック用
    boolean existsByProductName(String ProductName);
}