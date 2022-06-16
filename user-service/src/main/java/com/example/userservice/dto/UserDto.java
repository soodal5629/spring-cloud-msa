package com.example.userservice.dto;

import com.example.userservice.data.UserData;
import com.example.userservice.vo.ResponseOrder;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto extends UserData { // 중간 단계 클래스로 이동할 때 사용
    private String email;
    private String name;
    private String pwd;

    private String userId;
    private Date createdAt;
    private String encryptedPwd;

    private List<ResponseOrder> orders;
}
