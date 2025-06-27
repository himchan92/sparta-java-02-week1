package com.sparta.java02.domain.user.repository;

import static com.sparta.java02.domain.purchase.entity.QPurchase.purchase;
import static com.sparta.java02.domain.user.entity.QUser.user;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.java02.domain.user.dto.QUserPurchaseResponse;
import com.sparta.java02.domain.user.dto.UserPurchaseResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository {

  private final JPAQueryFactory queryFactory; //QueryDSL 사용목적

  public Page<UserPurchaseResponse> findUsers(String name, String email, Pageable pageable) {
    List<UserPurchaseResponse> users = queryFactory
        //QueryProjection : QueryDSL 제공 User, Purchase 두 테이블 조합한 필드들로 Response 만들어 활용 지원
        .select(new QUserPurchaseResponse(
            user.id,
            user.name,
            user.email,
            purchase.id,
            purchase.totalPrice.as("price") // response 응답 시 필드명이 서로 다르면 as 이용해서 셋팅가능
        ))
        .from(user)
        .join(purchase).on(user.eq(purchase.user))
        .where(
            nameContains(name),
            emailContains(email)
        )
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch(); // 다건 처리 반영으로 List형태 DB 수행

    //총 갯수 구하기
    long totalCount = queryFactory.select(user.count())
        .from(user)
        .join(purchase).on(user.eq(purchase.user))
        .where(
            nameContains(name),
            emailContains(email)
        )
        .fetchOne(); //단건 처리 반영 단일값 형태 DB 수행

    //파라미터순서: 대상 엔티티 조회결과, 페이징참조변수, 총갯수
    return new PageImpl<>(users, pageable, totalCount); //Page 에서 제공하는 객체
  }

  private BooleanExpression nameContains(String name) {
    return StringUtils.hasText(name) ? user.name.contains(name) : null;
  }

  private BooleanExpression emailContains(String email) {
    return StringUtils.hasText(email) ? user.email.contains(email) : null;
  }
}
