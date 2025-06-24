package com.sparta.java02.global.exception;

import com.sparta.java02.common.exception.ServiceException;
import com.sparta.java02.common.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 스프링이게 핸들링할것 있다고 통보
public class GlobalExceptionHandler {

  //에러처리 핸들링

  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<?> handleResponseException(ServiceException ex) {
    return ApiResponse.error(ex.getCode(), ex.getMessage());
  }
}