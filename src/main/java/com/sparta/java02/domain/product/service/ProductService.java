package com.sparta.java02.domain.product.service;

import com.sparta.java02.domain.product.dto.ProductRequest;
import com.sparta.java02.domain.product.dto.ProductResponse;
import com.sparta.java02.domain.product.entity.Product;
import com.sparta.java02.domain.product.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //final 필드 생성자 생성 지원
public class ProductService {

  //필드방식, Autowired 필수
//  @Autowired
//  private ProductRepository productRepository;

  private final ProductRepository productRepository; //final 붙어 불변성 보장

  public List<ProductResponse> getAll() {
    return new ArrayList<>();
  }

  public ProductResponse getById(Long id) {
    return null;
  }

  public ProductRequest create(Product request) {
    return null;
  }

  public ProductResponse update(Long id, ProductRequest request) {
    return null;
  }

  public void delete(Long id) {

  }

  //생성자 호출시점 생성
//  public ProductService(ProductRepository productRepository) {
//    this.productRepository = productRepository;
//  }

  //setter 주입방식으로 Autowired 필수, 생성후에도 변경가능하여 final 불가능
//  private ProductRepository productRepository;
//  @Autowired
//  public void setProductRepository(ProductRepository productRepository) {
//    this.productRepository = productRepository;
//  }
}
