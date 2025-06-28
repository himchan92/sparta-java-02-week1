package com.sparta.java02.domain.user.mapper;

import com.sparta.java02.domain.user.dto.UserResponse;
import com.sparta.java02.domain.user.dto.UserSearchResponse;
import com.sparta.java02.domain.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") //spring에 빈주입 필수설정
public interface UserMapper {

  // source : 파라미터의 User 대상
  // target : 타겟이되는 엔티티쪽 필드명
  // 필드명이 서로다를때 매핑시켜주기위함
  //@Mapping(target = "userEmail", source = "email")
  UserResponse toResponse(User user);

  UserSearchResponse toSearch(User user);

  //User toEntity(UserCreateRequest request);
}
