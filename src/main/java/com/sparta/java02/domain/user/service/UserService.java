package com.sparta.java02.domain.user.service;

import com.sparta.java02.common.exception.ServiceException;
import com.sparta.java02.common.exception.ServiceExceptionCode;
import com.sparta.java02.domain.purchase.entity.Purchase;
import com.sparta.java02.domain.purchase.repository.PurchaseRepository;
import com.sparta.java02.domain.user.dto.UserSearchResponse;
import com.sparta.java02.domain.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  //리포지토리는 다른 영역으로 도메인 상관없어 참조 가능
  private final PurchaseRepository purchaseRepository;

  public List<UserSearchResponse> searchAll(Long userId) {

    //리포지터리통해 참조하는경우는 도메인주도개발 관점에서 고민필요...
    Purchase purchase = purchaseRepository.findById(userId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_DATA));

    if (ObjectUtils.isEmpty(userId)) {
      throw new ServiceException(ServiceExceptionCode.NOT_FOUND_USER);
    }

    return new ArrayList<>();
  }

  public void save() {

  }
}