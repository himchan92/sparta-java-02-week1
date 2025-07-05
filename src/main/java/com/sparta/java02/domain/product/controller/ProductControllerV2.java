package com.sparta.java02.domain.product.controller;

import com.sparta.java02.domain.product.dto.ProductRequest;
import com.sparta.java02.domain.product.dto.ProductResponse;
import com.sparta.java02.domain.product.entity.Product;
import com.sparta.java02.domain.product.service.ProductService;
import io.micrometer.core.ipc.http.HttpSender.Response;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 별도 요청, 응답 DTO 적용 후
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/products")
public class ProductControllerV2 {

  private final ProductService productService;

  //전체 조회
  @GetMapping
  public ResponseEntity<List<ProductResponse>> getAll() {
    return ResponseEntity.ok(productService.getAll());
  }

  //단일 조회
  @GetMapping("/{id}")
  public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
    return ResponseEntity.ok(productService.getById(id));
  }

  //등록
  // 변경 호출방식에는 Body에 값 전달하기위해 @RequestBody 필수설정
  @PostMapping
  public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
    //201 created 상태코드와 함께 응답
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(productService.create(request));
  }

  //수정
  // 변경 호출방식에는 Body에 값 전달하기위해 @RequestBody 필수설정
  @PutMapping("/{id}")
  public ResponseEntity<ProductResponse> update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
    return ResponseEntity.ok(productService.update(id, request));
  }

  //삭제
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    productService.delete(id);

    //204 No Content 으답
    return ResponseEntity.noContent().build();
  }
}