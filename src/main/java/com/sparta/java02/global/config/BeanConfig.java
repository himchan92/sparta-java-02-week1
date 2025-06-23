package com.sparta.java02.global.config;

import com.sparta.java02.domain.user.service.UserServiceImplV2;
import com.sparta.java02.domain.user.service.UserService_v1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 해당 클래스를 스프링프로젝트의 셋팅파일역할로 부여
public class BeanConfig {

  //@Bean : 스프링컨테이너에게 아래 메소드 내용 인식되게 처리, 구현로직의 인스턴스 처리
  //        싱글턴패턴으로써 @Bean에 등록되어 인스턴스된것은 무조건 하나만 존재하는법칙
  //        싱글턴패턴이기에 @Bean 등록된 동일한 메소드 있으면 에러발생
  @Bean
  public UserService_v1 userService() {
    return new UserServiceImplV2();
  }
}
