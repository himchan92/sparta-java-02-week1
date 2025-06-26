package com.sparta.java02.domain.purchase.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseRequest {

  Long id;

  BigDecimal totalPrice;

  LocalDateTime createdAt;
}
