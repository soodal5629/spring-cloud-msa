package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.Greeting;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
public class UserController {
    private Environment env;
    private UserService userService;

    @Autowired // 권장하지 않지만 여기서는 간단한 예제이므로..
    private Greeting greeting;

    public UserController(Environment env, UserService userService) {
        this.env = env;
        this.userService = userService;
    }

    @GetMapping("/health-check")
    public String status(){
        return "It's Working in User Service";
    }

    @GetMapping("/welcome")
    public String welcome(){
        //return env.getProperty("greeting.message");
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user){
        UserDto userDto = UserMapper.INSTANCE.toDto(user);
        userService.createdUser(userDto);
        ResponseUser responseUser = UserMapper.INSTANCE.toResponseUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

}
