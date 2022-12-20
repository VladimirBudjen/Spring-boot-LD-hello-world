package com.darkly.launch.controller;

import com.darkly.launch.service.Service;
import com.launchdarkly.sdk.LDUser;
import com.launchdarkly.sdk.LDValue;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.darkly.launch.util.UtilStrings.SESSION_USER;

@RestController
@RequestMapping(value = "/test")
public class Controller {
    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> test( @RequestBody UserDto userDto, HttpSession session) {
        session.setAttribute(SESSION_USER, generateLDUser(userDto));
        return ResponseEntity.ok(service.doSomething());
    }

    private LDUser generateLDUser(UserDto userDto){
        return new LDUser.Builder(userDto.id())
                .firstName(userDto.firstName())
                .lastName(userDto.lastName())
                .custom("groups", LDValue.buildArray().add(userDto.group()).build())
                .build();
    }
}
