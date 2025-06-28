package com.sparta.java02.domain.product.controller;

import com.sparta.java02.common.response.ApiResponse;
import com.sparta.java02.domain.product.dto.ProductRequest;
import com.sparta.java02.domain.product.dto.ProductResponse;
import com.sparta.java02.domain.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products") // API URL 전체 공통경로
@RequiredArgsConstructor //final 붙은 필드 대상 생성자 주입 지원(롬복)
public class ProductControllerV1 {

  //@Autowired //필드주입방식 -> 비권장
  //private ProductService productService;

//  @Autowired //생성자 주입방식 -> 실행시 가장먼저 발생되어 많이 사용
//  public ProductControllerV1(ProductService productService) {
//    this.productService = productService;
//  }

//  @Autowired //setter 주입방식
//  public void setProductService(ProductService productService) {
//    this.productService = productService;
//  }

  private final ProductService productService;

  //GlobalExceptionHandler이 오류 발생 시 예외를 다 받아 처리하기에 성공로직만 넣어도 된다.
  // URI, 자원 : 자원은 정보의 대상으로 명사로 표현해야한다!!
  // 부득이하게 CRUD로 표현하기 어려운 동작은 URI 끝에 동사(예시 approve)를 명시해준다 (ex: /purchases/{userId}/approve)

  @GetMapping("/sample")
  public ResponseEntity<String> findProducts() {
    return ResponseEntity.ok("Hello World");
  }

  //@Valid 통해 DTO 필드에 설정한 검증어노테이션 체크수행
  @PostMapping("/sample")
  public ApiResponse<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
    return ApiResponse.success(productService.create(request));
  }
}