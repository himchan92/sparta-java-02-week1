package com.sparta.java02.domain.product.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.wildfly.common.annotation.NotNull;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE) //모든 필드 범위 private 제한
public class ProductRequest {

  Long categoryId;

  @NotNull //validation 지원으로 컨트롤러에서 @Valid 필요, 많이 쓰는 null 체크, @NotBlank는 문자열 전용으로 문자열일때만 사용
  String name;

  String description;

  @NotNull
  @Positive //값을 양수로만 제한
  BigDecimal price;

  @NotNull
  @PositiveOrZero //값을 양수 혹은 0으로만 제한
  Integer stock;

}
