package com.sparta.java02.domain.user.service;

import org.springframework.stereotype.Service;

//인터페이스 : 미리 정한 약속, 내부로직은 변경되어도 스펙은 고정
//@Service : 컴포넌트(스프링컨테이너 > 자동빈등록)를 포함하고있기에 적용 시 별도 스프링빈 로직을 구현안해도된다.
@Service
public interface UserService {

  void save();
}