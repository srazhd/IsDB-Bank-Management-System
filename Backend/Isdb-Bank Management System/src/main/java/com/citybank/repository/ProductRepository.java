package com.citybank.repository;

import com.citybank.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByIdAndIsActiveTrue(Long id);
    List<Product> findAllByIsActiveTrue();
}
