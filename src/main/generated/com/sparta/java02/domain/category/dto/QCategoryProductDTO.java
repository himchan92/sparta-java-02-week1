package com.sparta.java02.domain.category.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.sparta.java02.domain.category.dto.QCategoryProductDTO is a Querydsl Projection type for CategoryProductDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCategoryProductDTO extends ConstructorExpression<CategoryProductDTO> {

    private static final long serialVersionUID = 1804154311L;

    public QCategoryProductDTO(com.querydsl.core.types.Expression<String> categoryName, com.querydsl.core.types.Expression<String> productName, com.querydsl.core.types.Expression<? extends java.math.BigDecimal> price, com.querydsl.core.types.Expression<Integer> stock) {
        super(CategoryProductDTO.class, new Class<?>[]{String.class, String.class, java.math.BigDecimal.class, int.class}, categoryName, productName, price, stock);
    }

}

