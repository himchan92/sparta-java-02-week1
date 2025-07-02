package com.sparta.java02.domain.purchase.service;

import com.sparta.java02.common.constant.Constants;
import com.sparta.java02.common.enums.PurchaseStatus;
import com.sparta.java02.common.exception.ServiceException;
import com.sparta.java02.common.exception.ServiceExceptionCode;
import com.sparta.java02.domain.product.entity.Product;
import com.sparta.java02.domain.purchase.dto.PurchaseCancelResponse;
import com.sparta.java02.domain.purchase.dto.PurchaseProductResponse;
import com.sparta.java02.domain.purchase.entity.Purchase;
import com.sparta.java02.domain.purchase.entity.PurchaseProduct;
import com.sparta.java02.domain.purchase.repository.PurchaseProductRepository;
import com.sparta.java02.domain.purchase.repository.PurchaseRepository;
import com.sparta.java02.domain.user.repository.UserRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PurchaseCancelService {

  private final UserRepository userRepository;
  private final PurchaseRepository purchaseRepository;
  private final PurchaseProductRepository purchaseProductRepository;

  //해당 메소드 호출된 메소드에도 @Transactional 붙어있는데 생략안되고 둘다 붙어있어야 한다(추후, 동시성 개념등 배울때 자세히 안내예정)
  @Transactional
  public PurchaseCancelResponse cancel(Long purchaseId, Long userId) {
    Purchase purchase = purchaseRepository.findByIdAndUser_Id(purchaseId, userId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_DATA));

    //사전 취소가능한지 체크하고 가능(PENDING)하면 취소, 안되면 환불
    //대부분 equals로 비교하나 ENUM 일때만 != 같은 연산자로 비교하는게 낫다.
    validatePurchaseStatus(purchase);
    purchase.setStatus(PurchaseStatus.CANCELED);

    //구매목록 가져와야 재고수량을 알수있으니 수행
    //위 메소드 파라미터 purchaseId를 써도좋지만 이전 조회한 purchase.getId()를 바로써도 괜찮다.
    List<PurchaseProduct> purchaseProducts = purchaseProductRepository.findByPurchase_Id(purchase.getId());

    restoreProductStock(purchaseProducts);

    //결제 취소 API
    //주문 취소 알림

    List<PurchaseProductResponse> purchaseProductResponses = getPurchaseProductResponses(purchaseProducts);

    // Mapper 이용해서 리팩토링 가능
    // 메세지 같은 문구들은 공통쪽 상수값으로 따로 관리해도좋다.
    return PurchaseCancelResponse.builder()
        .purchaseId(purchase.getId())
        .purchaseStatus(purchase.getStatus())
        .canceledAt(LocalDateTime.now())
        .message(Constants.PURCHASE_CANCEL_MESSAGE)
        .cancelledProducts(purchaseProductResponses)
        .build();
  }

  private static List<PurchaseProductResponse> getPurchaseProductResponses(List<PurchaseProduct> purchaseProducts) {
    return purchaseProducts.stream()
        .map((purchaseProduct) -> {
          BigDecimal totalPrice = purchaseProduct.getPrice()
              .multiply(BigDecimal.valueOf(purchaseProduct.getQuantity()));

          return PurchaseProductResponse.builder()
              .productId(purchaseProduct.getId())
              .productName(purchaseProduct.getProduct().getName())
              .quantity(purchaseProduct.getQuantity())
              .price(purchaseProduct.getPrice())
              .totalPrice(totalPrice)
              .build();
        }).toList();
  }

  //취소가능상태인지 체크
  private static void validatePurchaseStatus(Purchase purchase) {
    if(purchase.getStatus() != PurchaseStatus.PENDING) {
      throw new ServiceException(ServiceExceptionCode.CANNOT_CANCEL);
    }
  }

  //재고수량원복
  private void restoreProductStock(List<PurchaseProduct> purchaseProducts) {
    for(PurchaseProduct purchaseProduct : purchaseProducts) {
      Product product = purchaseProduct.getProduct();
      product.increaseStock(purchaseProduct.getQuantity());
    }
  }
}
