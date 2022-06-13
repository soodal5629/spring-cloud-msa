package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.jpa.UserRepository;
import com.example.userservice.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createdUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString()); // uuid 생성
        UserEntity userEntity = UserMapper.INSTANCE.toEntity(userDto);
        userEntity.setEncryptedPwd("encrypted_password");

        userRepository.save(userEntity);

        return userDto;
    }
}
