package com.sparta.java02.domain.user.mapper;

import com.sparta.java02.domain.user.dto.UserResponse;
import com.sparta.java02.domain.user.dto.UserSearchResponse;
import com.sparta.java02.domain.user.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-30T21:12:03+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse toResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        userResponse.name( user.getName() );
        userResponse.email( user.getEmail() );
        userResponse.createdAt( user.getCreatedAt() );

        return userResponse.build();
    }

    @Override
    public UserSearchResponse toSearch(User user) {
        if ( user == null ) {
            return null;
        }

        UserSearchResponse.UserSearchResponseBuilder userSearchResponse = UserSearchResponse.builder();

        userSearchResponse.id( user.getId() );
        userSearchResponse.name( user.getName() );
        userSearchResponse.email( user.getEmail() );
        userSearchResponse.createdAt( user.getCreatedAt() );

        return userSearchResponse.build();
    }
}
