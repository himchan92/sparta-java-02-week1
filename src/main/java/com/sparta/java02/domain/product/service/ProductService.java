package com.sparta.java02.domain.product.service;

import com.sparta.java02.common.exception.ServiceException;
import com.sparta.java02.common.exception.ServiceExceptionCode;
import com.sparta.java02.domain.product.dto.ProductRequest;
import com.sparta.java02.domain.product.dto.ProductResponse;
import com.sparta.java02.domain.product.entity.Product;
import com.sparta.java02.domain.product.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Transactional - 서비스 계층에서만 사용 - 데이터와 직접소통은 Repository가 하고 Repository통해 받은 데이터를 로직화하는 역할이니
 * Repository에 로직들어가면안됨 데이터소통역할만 해야함 - 컨트롤러는 외부 URL 통해 클라이언트와 소통만 하여 데이터구조를 직접 알수 없는 역할
 */
@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  //전체조회
  public List<ProductResponse> getAll() {
    return new ArrayList<>();
  }

  //ID 기준 단건조회
  public ProductResponse getById(Long id) {
    // orElseThrow() 사용이유
    //- 값 존재하지 않을시 명시적 예외던져 오류발생 사전차단
    //- Optional, get 사용시 Exception 터져 비추
    //- new Exception 커스터마이징한것 호출통해 에러코드, 메세지 파악쉬워 유지보수 용이
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

    //빌더패턴 반환이유
    //- 안전하고 명확하게 만들기위함
    //- 각필드 및 값이 명시적으로 표현되어있어 파악쉬움
    //- 필드 명시 순서 상관없어 실수방지
    //- 값이 명확하여 개발자 실수 줄여줌
    //- 불변성으로써 변경불가능한 필드 생성유리
    return ProductResponse.builder()
        .id(product.getId())
        .categoryId(product.getCategoryId())
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .stock(product.getStock())
        .createdAt(product.getCreatedAt())
        .build();
  }

  public ProductResponse create(ProductRequest request) {
    return null;
  }

  public ProductResponse update(Long id, ProductRequest request) {
    return null;
  }

  public void delete(Long id) {

  }
}
