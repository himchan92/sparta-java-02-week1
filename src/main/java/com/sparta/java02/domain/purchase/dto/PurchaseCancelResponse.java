package com.sparta.java02.domain.purchase.dto;

import com.sparta.java02.common.enums.PurchaseStatus;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseCancelResponse {

  Long purchaseId;

  PurchaseStatus purchaseStatus;

  LocalDateTime canceledAt; //생성일시는 반드시 DB에 저장하여 이력남기는거 권장

  String message;

  List<PurchaseProductResponse> cancelledProducts;
}