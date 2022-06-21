package com.example.userservice.mapper;

import com.example.userservice.data.UserData;
import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.vo.ResponseOrder;
import com.example.userservice.vo.ResponseUser;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-20T10:51:15+0900",
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
    public UserDto toDto(UserData userData) {
        if ( userData == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.email( userData.getEmail() );
        userDto.name( userData.getName() );
        userDto.pwd( userData.getPwd() );
        userDto.userId( userData.getUserId() );
        userDto.createdAt( userData.getCreatedAt() );
        userDto.encryptedPwd( userData.getEncryptedPwd() );
        List<ResponseOrder> list = userData.getOrders();
        if ( list != null ) {
            userDto.orders( new ArrayList<ResponseOrder>( list ) );
        }

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
        userDto.pwd( userEntity.getPwd() );
        userDto.userId( userEntity.getUserId() );
        userDto.createdAt( userEntity.getCreatedAt() );
        userDto.encryptedPwd( userEntity.getEncryptedPwd() );
        List<ResponseOrder> list = userEntity.getOrders();
        if ( list != null ) {
            userDto.orders( new ArrayList<ResponseOrder>( list ) );
        }

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
        List<ResponseOrder> list = userDto.getOrders();
        if ( list != null ) {
            responseUser.setOrders( new ArrayList<ResponseOrder>( list ) );
        }

        return responseUser;
    }
}
