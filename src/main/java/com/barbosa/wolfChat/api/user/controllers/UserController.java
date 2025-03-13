package com.barbosa.wolfChat.api.user.controllers;

import com.barbosa.wolfChat.api.user.dtos.UserCrudDTO;
import com.barbosa.wolfChat.api.user.dtos.UserInsertCrudDTO;
import com.barbosa.wolfChat.api.user.dtos.UserRequest;
import com.barbosa.wolfChat.api.user.services.UserService;
import com.barbosa.wolfChat.core.models.entities.User;
import com.barbosa.wolfChat.api.user.services.UserServiceImpl;
import com.barbosa.wolfChat.utils.model.ResponseUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        Page<User> users = userServiceImpl.getAllUsers(pageable);
        return ResponseEntity.ok(users);
    }


    @GetMapping("/chat/init")
    public ResponseEntity<List<UserRequest>> getAllUsers() {
        System.out.println("CHEGOU EM GETALL USERS");
        List<UserRequest> userRequests = userServiceImpl.getUsers();
        return ResponseEntity.ok(userRequests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUtil> findById(@PathVariable Long id) {
        ResponseUtil responseUtil = userServiceImpl.getUserById(id);
        return new ResponseEntity<>(responseUtil,responseUtil.getStatus());
    }
    @PostMapping
    public ResponseEntity<ResponseUtil> addUser(@RequestBody UserInsertCrudDTO user) {
        ResponseUtil responseUtil = userServiceImpl.insertUser(user);
        return new ResponseEntity<>(responseUtil, responseUtil.getStatus());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseUtil> udpateUser(
            @PathVariable Long id,
            @RequestBody UserCrudDTO user) {
        ResponseUtil responseUtil = userServiceImpl.updateUser(id,user);
        return new ResponseEntity<>(responseUtil, responseUtil.getStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userServiceImpl.deleteUser(id);
        return ResponseEntity.noContent().build();
    }



}
