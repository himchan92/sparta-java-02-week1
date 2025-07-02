package com.sparta.java02.domain.category.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  
  String name;
  
  //부모: 자기참조통해 계층관계 형성
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  Category parent;

  //자식카테고리
  @OneToMany(mappedBy = "parent")
  List<Category> children = new ArrayList<>();
}
