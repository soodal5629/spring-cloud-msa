package com.example.userservice;

import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@RequiredArgsConstructor
public class TestDataInit {
    private final UserService userService;
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        log.info("test data init!!");
        userService.createdUser(UserDto.builder()
                .email("test@test")
                .name("cl")
                .pwd("testpassword")
                .build());
    }
}