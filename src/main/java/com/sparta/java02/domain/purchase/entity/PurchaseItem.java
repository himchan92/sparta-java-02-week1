package com.sparta.java02.domain.purchase.entity;

import com.sparta.java02.domain.product.entity.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  // ManyToMany는 비추하기에 아래처럼 각각 생성을 권장
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "purchase_id")
  Purchase purchase;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  Product product;

  Integer quantity;

  BigDecimal price;
}
