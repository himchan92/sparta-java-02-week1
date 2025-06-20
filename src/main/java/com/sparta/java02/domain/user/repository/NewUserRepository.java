package com.sparta.java02.domain.user.repository;

import com.sparta.java02.domain.user.entity.NewUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewUserRepository extends JpaRepository<NewUser, Long> {

}
