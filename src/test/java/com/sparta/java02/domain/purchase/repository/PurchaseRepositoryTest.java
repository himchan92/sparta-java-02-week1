package com.sparta.java02.domain.purchase.repository;

import com.sparta.java02.common.enums.PurchaseStatus;
import com.sparta.java02.domain.purchase.entity.Purchase;
import com.sparta.java02.domain.user.entity.User;
import com.sparta.java02.domain.user.repository.UserRepository;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@SpringBootTest
class PurchaseRepositoryTest {

  @Autowired
  private PurchaseRepository purchaseRepository;

  @Autowired
  private UserRepository userRepository;

  @Test
  void 저장() {
    User user = userRepository.save(User.builder().name("d").email("d").passwordHash("d").build());

    Purchase purchase = Purchase.builder()
        .user(user)
        .totalPrice(BigDecimal.valueOf(1000))
        .status(PurchaseStatus.PENDING)
        .build();

    Purchase savePurchase = purchaseRepository.save(purchase);
  }

  @Test
  void 수정() {
    User user = userRepository.save(
        User.builder().name("d2").email("d2").passwordHash("d2").build());

    Purchase purchase = Purchase.builder()
        .user(user)
        .totalPrice(BigDecimal.valueOf(1000))
        .status(PurchaseStatus.PENDING)
        .build();

    Purchase savePurchase = purchaseRepository.save(purchase);

    savePurchase.setStatus(PurchaseStatus.COMPLETION);
    purchaseRepository.save(savePurchase);
  }

  @Test
  void 삭제() {
    User user = userRepository.save(
        User.builder().name("d1").email("d1").passwordHash("d1").build());

    Purchase purchase = Purchase.builder()
        .user(user)
        .totalPrice(BigDecimal.valueOf(1000))
        .status(PurchaseStatus.PENDING)
        .build();

    Purchase savePurchase = purchaseRepository.save(purchase);
    purchaseRepository.delete(savePurchase);

//    List<Purchase> purchases = new ArrayList<>();
//    purchaseRepository.deleteAll(); //admin 기능 구현 시 사용되니 참고

  }

  @Test
  void 조회() {
    Purchase purchase = purchaseRepository.findById(2L)
        .orElseThrow(() -> new RuntimeException("조회내역이 없음!"));
    log.info("결과 : " + purchase.getId());
    log.info("결과 : " + purchase.getTotalPrice());
  }
}