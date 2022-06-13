package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDto createdUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString()); // uuid 생성
        UserEntity userEntity = userMapper.toEntity(userDto);
        return null;
    }
}
