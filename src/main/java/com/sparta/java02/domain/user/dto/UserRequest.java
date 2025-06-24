package com.sparta.java02.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.wildfly.common.annotation.NotNull;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

  //필수체크적용(validation gradle 제공)
  @NotNull
  String name;

  @Email //자동 이메일형식맞는지 검증
  String email;

  @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
  String password;

}