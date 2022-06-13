package com.example.userservice.mapper;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-13T15:09:19+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.email( userDto.getEmail() );
        userEntity.name( userDto.getName() );
        userEntity.userId( userDto.getUserId() );
        userEntity.encryptedPwd( userDto.getEncryptedPwd() );

        return userEntity.build();
    }

    @Override
    public UserDto toDto(RequestUser requestUser) {
        if ( requestUser == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.email( requestUser.getEmail() );
        userDto.name( requestUser.getName() );
        userDto.pwd( requestUser.getPwd() );

        return userDto.build();
    }

    @Override
    public UserDto entityToDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.email( userEntity.getEmail() );
        userDto.name( userEntity.getName() );
        userDto.userId( userEntity.getUserId() );
        userDto.encryptedPwd( userEntity.getEncryptedPwd() );

        return userDto.build();
    }

    @Override
    public ResponseUser toResponseUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        ResponseUser responseUser = new ResponseUser();

        responseUser.setEmail( userDto.getEmail() );
        responseUser.setName( userDto.getName() );
        responseUser.setUserId( userDto.getUserId() );

        return responseUser;
    }
}
