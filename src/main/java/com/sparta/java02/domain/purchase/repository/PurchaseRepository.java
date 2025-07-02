package com.sparta.java02.domain.purchase.repository;

import com.sparta.java02.domain.purchase.entity.Purchase;
import com.sparta.java02.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

  //언더바_Id : 언더바 구분이용해서 User 안에 Id를 접근한다는의미(JPA 메소드)
  Optional<Purchase> findByIdAndUser_Id(Long id, Long userId);

  Long user(User user);
}
