package com.example.userservice.vo;

import lombok.Data;

@Data
public class ResponseUser {
    /* response body 에 담아줄 내용들 */
    private String email;
    private String name;
    private String userId;
}
