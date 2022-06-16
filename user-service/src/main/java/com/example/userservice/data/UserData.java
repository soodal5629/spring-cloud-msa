package com.example.userservice.data;

import com.example.userservice.vo.ResponseOrder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserData {
    private String email;
    private String name;
    private String pwd;

    private String userId;
    private Date createdAt;
    private String encryptedPwd;

    private List<ResponseOrder> orders;
}
