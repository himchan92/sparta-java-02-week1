package com.sparta.java02.domain.category.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class CategoryProductDTO {

  private final String categoryName;
  private final String productName;
  private final BigDecimal price;
  private final Integer stock;

  @QueryProjection //빌드 시 해당 객체를 Q 객체화
  public CategoryProductDTO(String categoryName, String productName, BigDecimal price,
      Integer stock) {
    this.categoryName = categoryName;
    this.productName = productName;
    this.price = price;
    this.stock = stock;
  }
}