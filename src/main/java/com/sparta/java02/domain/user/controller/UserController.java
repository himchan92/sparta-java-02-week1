package com.sparta.java02.domain.user.controller;

import com.sparta.java02.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// 스프링컨테이너는 빈등록이 된것만 관리대상으로 삼는다.
// 계층형 아키텍처: 로직처리는 되도록 서비스단안에서 처리할것
@Controller // 자주쓰는 Controller, Service, Repository 등 어노테이션 안에 빈등록대신해주는 Component가 내재되어있다.
@RequiredArgsConstructor // final 붙은 필드 대상 생성자 DI 지원
public class UserController {

  //private final UserService userService = new UserService();

  // 객체지향 오류 : 구현제 바꿨는데 참조변수 타입까지 바꾸는것은 잘못된 방식
  // 잘못된 방식 : 스펙이 변경된경우 자료형까지 변경안하면 장애발생되기에 안좋다
  // 개선된 방식 : 인터페이스를 도입
  //private final UserServiceV2 userService = new UserServiceV2();

  //인터페이스 구현에 의한 다형성 방식 : 자료형은 인터페이스로 미리정한 약속이기에 변경안해도되고 구현로직만 변경하면 되는 장점
  //private UserService userService = new UserServiceImplV1();

  //IOC(제어의 역전) : 개발자가 직접 new 키워드로 인스턴스 수행아닌 스프링컨테이너가 대신 수행
  // Autowired : 스프링컨테이너가 해당 어노테이션 붙은 객체에 인스턴스를 부여(=주입)
  @Autowired
  private UserService userService; //필드방식 주입(Autowired 작성 필수)

  //생성자 주입방식 : 필드아닌 생성자에 Autowired붙이면 UserController 생성 시 싱글턴방식의 @Bean 적용된 메소드안에서 생성된 객체를 주입
  //                생성자 주입때는 final 필수 붙일것
  //                필드, setter, 생성자 주입방식중에서 가장 권장되는 방식
  private final UserService userService2;

  //@Autowired -> 생략가능하고 롬복에서 생성자조차도 생략되어 생성자 DI를 제공해준다.(@RequiredArgsConstructor)
  public UserController(UserService userService2) {
    this.userService2 = userService2;
  }

  // SETTER 주입방식(Autowired 작성 필수)
  private UserService userService3;

  @Autowired
  public void setUserService(UserService userService3) {
    this.userService3 = userService3;
  }

  public void save() {
    userService.save();
  }
}
