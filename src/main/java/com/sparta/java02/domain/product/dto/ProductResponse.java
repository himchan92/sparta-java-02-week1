package com.sparta.java02.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder //응답값 개선된 생성자 검증 지원
@FieldDefaults(level = AccessLevel.PRIVATE) //모든 필드 범위 private 제한
public class ProductResponse {

  Long id;

  Long categoryId;

  String name;

  String description;

  double price;

  Integer stock;

  //문자열 yyyy-MM-dd HH:mm:ss 포맷으로 JSON 결과 반환설정
  @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  LocalDateTime createdAt;

}
