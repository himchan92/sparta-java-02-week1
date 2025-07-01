package com.sparta.java02.domain.purchase.repository;

import com.sparta.java02.domain.purchase.entity.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, Long> {

}
