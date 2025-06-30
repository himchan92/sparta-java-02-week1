package com.sparta.java02.global.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component // 해당 클래스가 스프링컨테이너 등록되게 필수셋팅
public class LoggingAspect {

  //컨트롤러 패키지 하위를 대상
  @Before("execution(* com.sparta.java02.domain..controller..*(..))")
  public void logBeforeApiExecution() {
    log.info("Logging Before API 메소드 실행 전 로그");
  }

  @Before("within(com.sparta.java02.domain..*)")
  public void logBeforeWithin() {
    log.info("[within] domain 패키지 내부 메서드 실행 전 로그");
  }
}
