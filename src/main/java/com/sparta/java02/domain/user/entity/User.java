package com.sparta.java02.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity //해당 객체는 JPA가 관리하는 엔티티라고 명시
@Getter
@DynamicInsert //null 아닌것만 insert
@DynamicUpdate //null 아닌것만 update
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Table(name = "user") //테이블생성이 이름을 user로 설정, 객체명이 동일하면 생략가능
@FieldDefaults(level = AccessLevel.PRIVATE) //모든 필드 자료형 private 적용
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
  Long id;

  @Column(nullable = false, length = 50) //name 속성은 필드명 그대로 컬럼할경우 생략
  String username;

  @Column(nullable = false, unique = true)
  String email;

  @Column(nullable = false, name = "password") //DB컬럼은 언더바 권장이라 name으로 설정
  String password;

  @CreationTimestamp //엔티티 생성시 자동 시간부여
  @Column(name = "create_at", nullable = false, updatable = false) //생성시점은 필수기록이니 nullable = false 부여
  LocalDateTime createdAt;

  @UpdateTimestamp //엔티티 수정 시 자동 시간부여
  @Column(name = "updated_at")
  LocalDateTime updatedAt;

  @Builder //전체 노출시 클래스에 붙여도 상관없으나 제한된 필드만 노출시키고싶은경우 별도 생성자 생성하여 명시
  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public void changeName(String username) {
    this.username = username;
  }
}
