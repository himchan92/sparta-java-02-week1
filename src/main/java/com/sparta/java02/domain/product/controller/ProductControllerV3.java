package com.sparta.java02.domain.product.controller;

import com.sparta.java02.common.response.ApiResponse;
import com.sparta.java02.domain.product.dto.ProductResponse;
import com.sparta.java02.domain.product.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v3/products")
public class ProductControllerV3 {

  private final ProductService productService;

  //전체 상품 조회
  @GetMapping
  public ApiResponse<List<ProductResponse>> getAll() {
    return ApiResponse.success(productService.getAll());
  }
}
