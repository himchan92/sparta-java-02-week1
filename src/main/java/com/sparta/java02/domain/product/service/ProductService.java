package com.sparta.java02.domain.product.service;

import com.sparta.java02.domain.product.dto.ProductRequest;
import com.sparta.java02.domain.product.dto.ProductResponse;
import com.sparta.java02.domain.product.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public List<ProductResponse> getAll() {
    return null;
  }

  public ProductResponse getById(Long id) {
    return null;
  }

  public ProductResponse create(ProductRequest product) {
    return null;
  }

  public ProductResponse update(Long id, ProductRequest product) {
    return null;
  }

  public void delete(Long id) {

  }
}
