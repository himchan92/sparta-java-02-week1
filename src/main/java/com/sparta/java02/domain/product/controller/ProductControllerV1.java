package com.sparta.java02.domain.product.controller;

import com.sparta.java02.domain.product.entity.Product;
import com.sparta.java02.domain.product.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 별도 요청, 응답 DTO 적용 전
@RestController
@RequiredArgsConstructor //생성자 주입위한 롬복제공 어노테이션
@RequestMapping("/api/v1/products")
public class ProductControllerV1 {

  private final ProductService productService;

  // 다건 조회
  @GetMapping
  public List<Product> getAll() {
    //return productService.getAll();
    return null;
  }

  // 단일항목조회
  @GetMapping("/{id}")
  public Product getById(@PathVariable Long id) {
    //return productService.getById(id);
    return null;
  }

  // 등록
  @PostMapping
  public Product create(@RequestBody Product product) {
    //return productService.create(product);
    return null;
  }

  // 변경
  @PutMapping("/{id}")
  public Product update(@PathVariable Long id, @RequestBody Product product) {
    //return productService.update(id, product);
    return null;
  }

  // 삭제
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    //productService.delete(id);
  }
}