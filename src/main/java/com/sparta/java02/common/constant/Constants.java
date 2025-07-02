package com.sparta.java02.common.constant;

public class Constants {

  // 변경 되면 일일이 찾아서 고치는 번거로움을 덜하기위해 별도 공통로직으로 관리
  // EXCEPTION 처럼 ENUM 으로 관리하는것은 오류메세지관리 등 기준이란게 있어야되는데 그게아니라 단순 메세지 상수관리인경우 ENUM 할필요없고 공통기준이란게 있을시 ENUM 하면됨
  public static final String PURCHASE_CANCEL_MESSAGE = "구매가 성공적으로 취소되었습니다.";
}
