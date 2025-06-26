package com.sparta.java02.domain.purchase.service;

import com.sparta.java02.common.exception.ServiceException;
import com.sparta.java02.common.exception.ServiceExceptionCode;
import com.sparta.java02.common.response.ApiResponse;
import com.sparta.java02.domain.product.entity.Product;
import com.sparta.java02.domain.product.repository.ProductRepository;
import com.sparta.java02.domain.purchase.dto.PurchaseRequest;
import com.sparta.java02.domain.purchase.repository.PurchaseRepository;
import com.sparta.java02.domain.user.entity.User;
import com.sparta.java02.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PurchaseService {

  private final ProductRepository productRepository;
  private final PurchaseRepository purchaseRepository;

  //금지1. 다른 도메인의 Service를 참조하지 말것(실행은 되나 바람직하지 않다)
  //권장1. 다른 도메인의 Repository를 참조하자
  private final UserRepository userRepository;

  //허용1. 같은도메인분류 서비스는 순환참조 적기에 허용
  private PurchaseCancelService purchaseCancelService;

  @Transactional
  public ApiResponse<PurchaseRequest> findById(Long productId, PurchaseRequest request) {

    User user = userRepository.findById(productId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_USER));

    Product product = productRepository.findById(request.getProductId())
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

    if (product.getStock() < request.getQuantity()) {
      throw new ServiceException(ServiceExceptionCode.INSUFFICIENT_STOCK);
    }

    //product.decreaseStock(request.getQuantity()); //재고감소

    return ApiResponse.success();
  }
}
