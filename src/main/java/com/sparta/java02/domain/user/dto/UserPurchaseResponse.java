package com.sparta.java02.domain.user.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserPurchaseResponse {

  Long id;

  String name;

  String email;

  Long purchaseId;

  BigDecimal purchaseTotalPrice;

  @QueryProjection //사용 전 반드시 Build 처리 필수 -> Q 파일생성되어야 User, Purchase 두테이블 보여줄 필드 조합 Response 사용 가능
  public UserPurchaseResponse(Long id, String name, String email, Long purchaseId,
      BigDecimal purchaseTotalPrice) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.purchaseId = purchaseId;
    this.purchaseTotalPrice = purchaseTotalPrice;
  }
}
