package com.sparta.java02.domain.product.repository;

import com.sparta.java02.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository : 아래와 같이 지원된것 상속받는 경우 스프링부트내부 자동빈주입되어 생략가능, 단 수동으로 직접만든경우 붙여줘야함
public interface ProductRepository extends JpaRepository<Product, Long> {

}
