package com.sparta.java02.domain.product.service;

import com.sparta.java02.domain.product.entity.Product;
import com.sparta.java02.domain.product.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public List<Product> getAll() {
    return null;
  }

  public Product getById(Long id) {
    return null;
  }

  public Product create(Product product) {
    return null;
  }

  public Product update(Long id, Product product) {
    return null;
  }

  public void delete(Long id) {

  }
}
