package com.sparta.java02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Java02Application {

  public static void main(String[] args) {
    SpringApplication.run(Java02Application.class, args);
    //빌더패턴통해 생성자 개선점
//    User user = User.builder()
//        .name("이름")
//        .email("이메일")
//        .passwordHash("패스워드")
//        // .passwordHash(null) -> 빌더패턴은 null 있는건 안넣으면 알아서 null 처리 장점
//        .build();
  }
}