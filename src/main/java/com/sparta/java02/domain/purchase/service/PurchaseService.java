package com.sparta.java02.domain.purchase.service;

import com.sparta.java02.common.exception.ServiceException;
import com.sparta.java02.common.exception.ServiceExceptionCode;
import com.sparta.java02.domain.purchase.entity.Purchase;
import com.sparta.java02.domain.purchase.repository.PurchaseRepository;
import com.sparta.java02.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseService {

  private final PurchaseRepository purchaseRepository;

  //금지1. 다른 도메인의 Service를 참조하지 말것(실행은 되나 바람직하지 않다)
  //권장1. 다른 도메인의 Repository를 참조하자
  private final UserRepository userRepository;

  //허용1. 같은도메인분류 서비스는 순환참조 적기에 허용
  private PurchaseCancelService purchaseCancelService;

  public final Purchase getPurchase(Long purchaseId) {
    return purchaseRepository.findById(purchaseId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_DATA));
  }
}
