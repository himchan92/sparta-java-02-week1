package com.sparta.java02.domain.user.service;

import com.sparta.java02.common.exception.ServiceException;
import com.sparta.java02.common.exception.ServiceExceptionCode;
import com.sparta.java02.domain.user.dto.UserCreateRequest;
import com.sparta.java02.domain.user.dto.UserResponse;
import com.sparta.java02.domain.user.dto.UserSearchResponse;
import com.sparta.java02.domain.user.dto.UserUpdateRequest;
import com.sparta.java02.domain.user.entity.User;
import com.sparta.java02.domain.user.mapper.UserMapper;
import com.sparta.java02.domain.user.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  //조회 메소드 명명규칙 차이
  // find~ : 값이 있든 없든 조회
  // get~ : 값이 있는것만 조회

  //동작, 분기처리 등 대부분로직은 서비스에서 수행하고 컨트롤러에서 분기처리 등 넣지말자
  private final UserRepository userRepository;

  //암호화 객체(시큐리티 셋팅 후 가능)
  //private final PasswordEncoder passwordEncoder;

  private final UserMapper userMapper; //mapstruct 사용

  @Transactional(readOnly = true)
  public List<UserSearchResponse> searchUser() {
//    return userRepository.findAll().stream()
//        .map((user) -> UserSearchResponse.builder()
//            .id(user.getId())
//            .name(user.getName())
//            .email(user.getEmail())
//            .createdAt(user.getCreatedAt())
//            .build())
//        .toList();
    //위와 같이 일일이 매칭할필요없이 아래 mapstruct 활요하여 간략히 하고 비즈니스에 집중할수있음
    return userRepository.findAll().stream()
        // 자바 람다식문법으로 userMapper.toSearch() 동일, 단, 파라미터가 단일일때만 사용가능, 멀티면 불가능
        .map(userMapper::toSearch)
        .toList();
  }

  @Transactional(readOnly = true)
  public UserResponse getUserById(Long userId) {
    User user = userRepository.findById(userId)
        //orElseThrow() 방식을 써서 Optional대신 활용 가능
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_USER));

    //Mapstructs 사용전 : 빌더로 아래 필드를 일일이 매핑로직 짜줘야해서 필드가 많을경우 불편한 단점
    //Mapstructs 사용후 : 일일이 매핑로직 짜는것을 자동화처리해주는 장점
//    return UserResponse.builder()
//        .id(user.getId())
//        .name(user.getName())
//        .email(user.getEmail())
//        .createdAt(user.getCreatedAt())
//        .build();

    //일일이 필드 매핑없이 대신 수행해줘서 비즈니스 로직집중시켜주는 장점
    return userMapper.toResponse(getUser(userId));
  }

  @Transactional(readOnly = true)
  public Optional<User> findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Transactional
  public void create(UserCreateRequest request) {
    //필드가 적으면 모를까 여러개일경우 일일이 매핑하면 시간오래걸려 mapstructs 활용하면 좋음
//    userRepository.save(User.builder()
//        .name(request.getName())
//        .email(request.getEmail())
//        .passwordHash(request.getPassword())
//        .build());

    //위와 같이 일일이 매칭할필요없이 아래 mapstruct 활요하여 간략히 하고 비즈니스에 집중할수있음
    //userRepository.save(userMapper.toEntity(request));
  }

//  @Transactional
//  public UserResponse createUser(UserCreateRequest request) {
//    //이메일 중복확인
//    if(userRepository.findByEmail(request.getEmail()).isPresent()) {
//      throw new ServiceException(ServiceExceptionCode.DUPLICATE_EMAIL);
//    }
//
//    //암호화
//    String encodePassword = passwordEncode.encode(request.getPassword());
//
//    // 3. DTO를 Entity로 변환하여 DB에 저장
//    User user = request.toEntity(encodedPassword);
//    User savedUser = userRepository.save(user);
//
//    // 4. Entity를 Response DTO로 변환하여 Controller에 반환
//    return UserResponse.fromEntity(savedUser);
//  }

  @Transactional
  public void update(Long userId, UserUpdateRequest request) {
    User user = getUser(userId);

//    user.setName(request.getName());
//    user.setEmail(request.getEmail());
    userRepository.save(user);
  }

  @Transactional
  public void updateUser(Long id, String newName) {
    User getUser = getUser(id);

    getUser.changeName(newName);

    // Transactional 있으면 setter처리(changeName)만 해줘도 변경감지로 UPDATE 수행되어 save 없어도됨
    //userRepository.save(getUser);
  }

  @Transactional
  public void delete(Long userId) {
    userRepository.delete(getUser(userId));
  }

  // 변경수행 전 대상조회하는 로직을 공통함수화
  private User getUser(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_USER));
  }
}