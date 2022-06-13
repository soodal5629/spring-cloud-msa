package com.example.userservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // null값은 response에 포함하지 않음
public class ResponseUser {
    /* response body 에 담아줄 내용들 */
    private String email;
    private String name;
    private String userId;

    private List<ResponseOrder> orders;
}
