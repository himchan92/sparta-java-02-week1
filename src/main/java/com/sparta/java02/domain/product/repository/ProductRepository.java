package com.sparta.java02.domain.product.repository;

import com.sparta.java02.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
