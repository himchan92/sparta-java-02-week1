package com.sparta.java02.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder //모든 필드 보여줄경우 클래스에 명시, 일부만 보여주고싶은경우 생성자에 명시하여 보여줄 필드 셋팅
@AllArgsConstructor
@JsonInclude(Include.NON_NULL) //필드중에 NULL이 있는경우 JSON 에서 제외시켜줌
public class ProductResponseV1 {

  private final String name;
  private final String description;
  private final BigDecimal price;

  //문자열 pattern 명시된 규칙으로 포맷지원
  @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private final LocalDateTime createdAt;
}
