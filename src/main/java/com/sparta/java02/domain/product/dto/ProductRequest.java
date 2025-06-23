package com.sparta.java02.domain.product.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.wildfly.common.annotation.NotNull;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {

  Long categoryId;

  @NotNull
  String name;

  String description;

  @NotNull
  @Positive
  BigDecimal price;

  @NotNull
  @PositiveOrZero
  Integer stock;

}
