package com.example.userservice.mapper;

import com.example.userservice.data.UserData;
import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.vo.ResponseUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "id", ignore = true)
    //@Mapping(target = "createdAt",  expression = "java(java.time.LocalDateTime.now())")
    UserEntity toEntity(UserDto userDto);

    UserDto toDto(UserData userData);

    UserDto entityToDto(UserEntity userEntity);

    ResponseUser toResponseUser(UserDto userDto);
}
