package com.sparta.java02.domain.user.dto;

import java.time.LocalDateTime;

import com.sparta.java02.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

  // mapstruct는 반드시 필드명이 서로 일치해야 매핑되니 주의해라
  Long id;

  String name;

  String email;

  LocalDateTime createdAt;

  /**
   * Mapstruct 적용 전 빌더패턴 필드별 매핑방식
   * 반복적인 매핑작업
   * - 매핑작성과정에서 철자오류등 휴먼에러
   * - 필드 변경될때마다 수정하는 불편함
   */
  public UserResponse toResponse(User user) {
    return UserResponse.builder()
            .id(user.getId())
            .name(user.getUsername())
            .email(user.getEmail())
            .createdAt(user.getCreatedAt())
            .build();
  }
}