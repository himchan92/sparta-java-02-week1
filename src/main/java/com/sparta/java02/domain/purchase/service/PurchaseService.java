package com.sparta.java02.domain.purchase.service;

import com.sparta.java02.domain.purchase.dto.PurchaseCancelRequest;
import com.sparta.java02.domain.purchase.dto.PurchaseCancelResponse;
import com.sparta.java02.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PurchaseService {

  private final PurchaseCancelService cancelService;
  private final UserRepository userRepository;

  @Transactional
  public PurchaseCancelResponse cancelPurchase(PurchaseCancelRequest request) {
    return cancelService.cancel(request.getPurchaseId(), request.getUserId());
  }
}
