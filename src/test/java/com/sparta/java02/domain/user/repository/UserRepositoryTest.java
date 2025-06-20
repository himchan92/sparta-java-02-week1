package com.sparta.java02.domain.user.repository;

import com.sparta.java02.domain.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  void 저장() {
    User user = User.builder()
        .name("홍길동")
        .email("3333423")
        .passwordHash("00000")
        .build();
  }
}