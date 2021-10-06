package com.ecommerce.blockchain.repository;

import com.ecommerce.blockchain.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<ProductMapping> findAllBy();
    List<ProductMapping> findByUserId(Long userId);
    List<ProductMapping> findProductsByStatusIsFalse();
    List<ProductMapping> findProductsByStatusIsTrue();
}
