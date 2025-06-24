package com.sparta.java02.domain.category.dto;

import java.util.List;

public class CategoryTreeDto {

  private Long id;
  private String name;
  List<CategoryTreeDto> children;
}
