package com.dailycodework.sbemailverificationdemo.user;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;



    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {

        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }
}
