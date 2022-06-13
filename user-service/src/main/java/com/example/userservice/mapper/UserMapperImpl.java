package com.example.userservice.mapper;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.vo.RequestUser;
import org.mapstruct.Mapper;

@Mapper
public class UserMapperImpl implements UserMapper{
    @Override
    public UserEntity toEntity(UserDto userDto) {
        if(userDto == null) {return null;}
        else{
            UserEntity.UserEntityBuilder userEntityBuilder = UserEntity.builder();
            return userEntityBuilder.userId(userDto.getUserId())
                    .name(userDto.getName())
                    .email(userDto.getEmail())
                    .encryptedPwd(userDto.getEncryptedPwd())
                    .build();
        }
    }

    @Override
    public UserDto toDto(RequestUser requestUser) {
        return null;
    }
}
