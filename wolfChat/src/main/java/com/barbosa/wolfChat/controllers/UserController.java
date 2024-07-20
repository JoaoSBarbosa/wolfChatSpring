package com.barbosa.wolfChat.controllers;

import com.barbosa.wolfChat.entities.User;
import com.barbosa.wolfChat.services.UserService;
import com.barbosa.wolfChat.utils.model.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ResponseUtil> addUser(@RequestBody User user) {
        ResponseUtil responseUtil = userService.insertUser(user);
        return new ResponseEntity<>(responseUtil, responseUtil.getStatus());
    }
}
