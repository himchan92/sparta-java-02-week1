package com.sparta.java02.domain.user.controller;

import com.sparta.java02.common.response.ApiResponse;
import com.sparta.java02.domain.user.dto.UserCreateRequest;
import com.sparta.java02.domain.user.dto.UserResponse;
import com.sparta.java02.domain.user.dto.UserSearchResponse;
import com.sparta.java02.domain.user.dto.UserUpdateRequest;
import com.sparta.java02.domain.user.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 스프링컨테이너는 빈등록이 된것만 관리대상으로 삼는다.
// 계층형 아키텍처: 로직처리는 되도록 서비스단안에서 처리할것
//@Controller // 자주쓰는 Controller, Service, Repository 등 어노테이션 안에 빈등록대신해주는 Component가 내재되어있다.
@RestController // Controller + ResponseBody(API 생성기능)
@RequiredArgsConstructor // final 붙은 필드 대상 생성자 DI 지원
@RequestMapping("/api/users") //호출시 하위 URL앞에 공통으로 붙을수 있어 공통 URL 사용 시 필요
public class UserController {

  // 객체지향 오류 : 구현제 바꿨는데 참조변수 타입까지 바꾸는것은 잘못된 방식
  // 잘못된 방식 : 스펙이 변경된경우 자료형까지 변경안하면 장애발생되기에 안좋다
  // 개선된 방식 : 인터페이스를 도입
  //private final UserService_v1 userService = new UserService_v1();
  //private final UserServiceV2 userService = new UserServiceV2();

  //인터페이스 구현에 의한 다형성 방식 : 자료형은 인터페이스로 미리정한 약속이기에 변경안해도되고 구현로직만 변경하면 되는 장점
  //private UserService_v1 userService = new UserServiceImplV1();

  //IOC(제어의 역전) : 개발자가 직접 new 키워드로 인스턴스 수행아닌 스프링컨테이너가 대신 수행
  // Autowired : 스프링컨테이너가 해당 어노테이션 붙은 객체에 인스턴스를 부여(=주입)
//  @Autowired
//  private UserService_v1 userService; //필드방식 주입(Autowired 작성 필수)
//
//  //생성자 주입방식 : 필드아닌 생성자에 Autowired붙이면 UserController 생성 시 싱글턴방식의 @Bean 적용된 메소드안에서 생성된 객체를 주입
//  //                생성자 주입때는 final 필수 붙일것
//  //                필드, setter, 생성자 주입방식중에서 가장 권장되는 방식
//  private final UserService_v1 userService2;
//
//  //@Autowired -> 생략가능하고 롬복에서 생성자조차도 생략되어 생성자 DI를 제공해준다.(@RequiredArgsConstructor)
//  public UserController(UserService_v1 userService2) {
//    this.userService2 = userService2;
//  }
//
//  // SETTER 주입방식(Autowired 작성 필수)
//  private UserService_v1 userService3;
//
//  @Autowired
//  public void setUserService(UserService_v1 userService3) {
//    this.userService3 = userService3;
//  }

  private final UserService userService;

  //같은 어노테이션에 URL이 동일한경우 오류나니 API설계시 같은 호출방식에선 URL 중복 x
  //required = false되면 필수입력아님
  //파라미터명이 동일하면 어노테이션만 작성 가능
  //실무에서는 응답값 200, 400 등은 별도 ENUM 타입으로 만들어서 호출하는방식으로 관리하는걸 추천
  //실무에서는 반환타입을 HashMap 타입 금지 : get 통해 값을 뽑아내기전까진 어떤 값인지 모르기에 다른 개발자들도 알수있게 정해진 규약으로 된 Response DTO 사용
  @GetMapping("/{userId}")
  public ApiResponse<List<UserSearchResponse>> findAll(@RequestParam(required = false) String email,
      @PathVariable Long userId) {
    return ApiResponse.success(userService.searchUser());
  }

  @GetMapping("/{userId}")
  public ApiResponse<UserResponse> findbyId(@PathVariable Long userId) {
    return ApiResponse.success(userService.getUserById(userId));
  }

  //API 스펙 확인되고 여러 개발자가 보기쉽게하가위해서 별도 Request, Response DTO 만들어 명시
  //@Valid : validation 지원함수를 적용위해 필수 설정
  @PostMapping
  public ApiResponse<Void> create(@Valid @RequestBody UserCreateRequest request) {
    userService.create(request);
    return ApiResponse.success();
  }

  @PutMapping("{userId}")
  public ApiResponse<Void> update(@PathVariable Long userId,
      @Valid @RequestBody UserUpdateRequest request) {
    userService.update(userId, request);

    return ApiResponse.success();
  }

  @DeleteMapping("{userId}")
  public ApiResponse<Void> delete(@PathVariable Long userId) {
    userService.delete(userId);

    return ApiResponse.success();
  }
}
