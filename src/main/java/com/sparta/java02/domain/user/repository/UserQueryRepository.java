package com.sparta.java02.domain.user.repository;

import static com.sparta.java02.domain.purchase.entity.QPurchase.purchase;
import static com.sparta.java02.domain.user.entity.QUser.user;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.java02.domain.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository {

  private final JPAQueryFactory queryFactory; //QueryDSL 사용목적

  public List<User> findUsers(String name, String email) {
    return queryFactory.select(user)
        .from(user)
        .join(purchase).on(user.eq(purchase.user))
        .where(
            nameContains(name),
            emailContains(email)
        )
        .fetch(); // 작성내용 실제 DB 반영처리
  }

  private BooleanExpression nameContains(String name) {
    return StringUtils.hasText(name) ? user.name.contains(name) : null;
  }

  private BooleanExpression emailContains(String email) {
    return StringUtils.hasText(email) ? user.email.contains(email) : null;
  }
}
