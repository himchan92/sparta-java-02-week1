package com.sparta.java02.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {

  @NotBlank(message = "사용자 이름은 필수입니다.")
  @Size(min = 2, max = 50)
  String name;

  @NotBlank(message = "이메일은 필수입니다.")
  @Email(message = "유효한 이메일 형식이 아닙니다.")
  String email;

  @NotBlank(message = "비밀번호는 필수입니다.")
  String password;
}
