package com.sparta.java02.domain.purchase.repository;

import com.sparta.java02.domain.purchase.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
