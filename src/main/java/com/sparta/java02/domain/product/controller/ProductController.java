package com.sparta.java02.domain.product.controller;

import com.sparta.java02.common.response.ApiResponse;
import com.sparta.java02.domain.product.dto.ProductResponse;
import com.sparta.java02.domain.product.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Controller + RequestBody(JSON 반환 지원)
@RequiredArgsConstructor //final 필드 생성자 DI 지원
@RequestMapping("/api/products") //모든 URL 공통 경로
public class ProductController {

  private final ProductService productService;

  //전체 조회
  @GetMapping
  public ApiResponse<List<ProductResponse>> getAll() {
    return ApiResponse.success(productService.getAll());
  }

  //단일 상품 조회
  @GetMapping("/{id}")
  public ApiResponse<ProductResponse> getById(@PathVariable Long id) {
    return ApiResponse.success(productService.getById(id));
  }
}
