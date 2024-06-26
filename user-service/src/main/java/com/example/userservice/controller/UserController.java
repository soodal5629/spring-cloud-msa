package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.Greeting;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private Environment env;
    private UserService userService;

    @Autowired // 권장하지 않지만 여기서는 간단한 예제이므로..
    private Greeting greeting;

    @Autowired
    public UserController(Environment env, UserService userService) {
        this.env = env;
        this.userService = userService;
    }

    @GetMapping("/health-check")
    @Timed(value = "users.status", longTask = true)
    public String status(){
        return String.format("It's Working in User Service"
                + ", port(local.server.port)=" + env.getProperty("local.server.port")
                + ", port(server.port)=" + env.getProperty("server.port")
                + ", token secret=" + env.getProperty("token.secret")
                + ", token expiration time=" + env.getProperty("token.expiration_time"));
    }

    @GetMapping("/welcome")
    @Timed(value = "users.welcome", longTask = true)
    public String welcome(){
        //return env.getProperty("greeting.message");
        return greeting.getMessage();
    }

    /* 사용자 등록 */
    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user){
        UserDto userDto = UserMapper.INSTANCE.toDto(user);
        userService.createdUser(userDto);
        ResponseUser responseUser = UserMapper.INSTANCE.toResponseUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(){
        Iterable<UserEntity> userList = userService.getUserByAll();
        List<UserDto> result = new ArrayList<>();
        userList.forEach(v -> {
            result.add(UserMapper.INSTANCE.entityToDto(v));
        });
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userId){
        UserDto userDto = userService.getUserByUserId(userId);
        System.out.println("userDto = " + userDto);
        ResponseUser responseUser = UserMapper.INSTANCE.toResponseUser(userDto);
        System.out.println("responseUser = " + responseUser);
        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

}
