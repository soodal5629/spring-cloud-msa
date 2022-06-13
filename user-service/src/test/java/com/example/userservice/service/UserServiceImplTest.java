package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.vo.RequestUser;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


class UserServiceImplTest {

    @Test
    public void dtoToEntity(){
        UserDto userDto = UserDto.builder()
                .userId(UUID.randomUUID().toString())
                .name("user1")
                .email("aaa@nate.com")
                .pwd("dddddddd")
                .createdAt(new Date())
                .encryptedPwd("ddddddddd")
                .build();
        UserEntity userEntity = UserMapper.INSTANCE.toEntity(userDto);
        assertThat(userDto.getEmail()).isEqualTo(userEntity.getEmail());

    }

    @Test
    public void requestUserToDto(){
        RequestUser requestUser = RequestUser.builder()
                .name("user2")
                .email("aaa@naver.com")
                .pwd("asdfasdf")
                .build();
        UserDto userDto = UserMapper.INSTANCE.toDto(requestUser);
        assertThat(requestUser.getEmail()).isEqualTo(userDto.getEmail());

    }
}