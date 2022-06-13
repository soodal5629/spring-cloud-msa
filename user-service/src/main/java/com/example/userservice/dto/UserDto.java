package com.example.userservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserDto { // 중간 단계 클래스로 이동할 때 사용
    private String email;
    private String name;
    private String pwd;

    private String userId;
    private Date createdAt;
    private String encryptedPwd;
}
