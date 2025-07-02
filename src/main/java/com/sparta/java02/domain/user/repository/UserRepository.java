package com.sparta.java02.domain.user.repository;

import com.sparta.java02.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  // null 처리는 무조건 Optional 사용해야 방지됨 (끝에 .get() 레거시방식 절대금지!!)
  // JPA 제공함수아닌 임의로 메소드명 명시할시 @Query 통해 내용물 구현 가능(엔티티 대상 JPQL)
  // :name : name 파라미터 매핑
  @Query("SELECT u FROM User u WHERE u.username = :username")
  Optional<User> findUser(String name);

  // Fetch Join 성능개선 예제
//  @Query("SELECT u FROM User u JOIN FETCH u.purchases")
//  List<User> findAllByWithPurchases();

  Optional<User> findByEmail(String email);

  //이메일 기준 사용자조회 @Query
  //null 값있을수 있어 Optional 사용
  @Query("SELECT u FROM User u WHERE u.email = :email")
  Optional<User> findUserByEmail(@Param("email") String email);
}