package com.sparta.java02.domain.purchase.service;

import com.sparta.java02.common.exception.ServiceException;
import com.sparta.java02.common.exception.ServiceExceptionCode;
import com.sparta.java02.domain.product.entity.Product;
import com.sparta.java02.domain.product.repository.ProductRepository;
import com.sparta.java02.domain.purchase.dto.PurchaseProductRequest;
import com.sparta.java02.domain.purchase.dto.PurchaseRequest;
import com.sparta.java02.domain.purchase.entity.Purchase;
import com.sparta.java02.domain.purchase.entity.PurchaseProduct;
import com.sparta.java02.domain.purchase.repository.PurchaseProductRepository;
import com.sparta.java02.domain.purchase.repository.PurchaseRepository;
import com.sparta.java02.domain.user.entity.User;
import com.sparta.java02.domain.user.repository.UserRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PurchaseService {

  private final PurchaseRepository purchaseRepository;
  private final ProductRepository productRepository;
  private final PurchaseProductRepository purchaseProductRepository;
  private final UserRepository userRepository;

  @Transactional
  public Purchase purchase(PurchaseRequest request) {

    BigDecimal totalPrice = BigDecimal.ZERO;
    List<PurchaseProduct> purchaseProducts = new ArrayList<>();

    //구매 대상 조회
    //튜터님: findByID 같은 도메인 여러개면 공통화해도 괜찮으나 각각 한번씩 조회되는경우 비추천
    User user = userRepository.findById(request.getUserId())
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_USER));

    //구매 대상 저장
    Purchase purchase = savePurchase(user);

    //구매 처리
    totalPrice = createPurchases(request, purchase, purchaseProducts, totalPrice);

    //구매정보 업데이트 및 저장
    updatePurchase(purchase, totalPrice, purchaseProducts);

    return purchase;
  }

  private void updatePurchase(Purchase purchase, BigDecimal totalPrice,
      List<PurchaseProduct> purchaseProducts) {
    purchase.setTotalPrice(totalPrice);
    purchaseProductRepository.saveAll(purchaseProducts);
  }

  private BigDecimal createPurchases(PurchaseRequest request, Purchase purchase,
      List<PurchaseProduct> purchaseProducts, BigDecimal totalPrice) {
    for (PurchaseProductRequest itemRequest : request.getPurchaseItems()) {
      Product product = productRepository.findById(itemRequest.getProductId())
          .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

      //재고수량 체크 및 감소
      validateReduceStock(itemRequest, product);

      PurchaseProduct purchaseProduct = PurchaseProduct.builder()
          .product(product)
          .purchase(purchase)
          .quantity(itemRequest.getQuantity())
          .price(product.getPrice())
          .build();

      purchaseProducts.add(purchaseProduct);
      totalPrice = totalPrice.add(
          product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())));
    }
    return totalPrice;
  }

  private void validateReduceStock(PurchaseProductRequest itemRequest, Product product) {
    if (itemRequest.getQuantity() > product.getStock()) {
      throw new ServiceException(ServiceExceptionCode.OUT_OF_STOCK_PRODUCT);
    }

    product.reduceStock(itemRequest.getQuantity());
  }

  private Purchase savePurchase(User user) {
    Purchase purchase = purchaseRepository.save(Purchase.builder()
        .user(user)
        .build());
    return purchase;
  }
}
