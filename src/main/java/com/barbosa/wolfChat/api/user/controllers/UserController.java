package com.barbosa.wolfChat.api.user.controllers;

import com.barbosa.wolfChat.api.user.dtos.UserCrudDTO;
import com.barbosa.wolfChat.api.user.dtos.UserInsertCrudDTO;
import com.barbosa.wolfChat.models.entities.User;
import com.barbosa.wolfChat.api.user.services.UserService;
import com.barbosa.wolfChat.utils.model.ResponseUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        Page<User> users = userService.getAllUsers(pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUtil> findById(@PathVariable Long id) {
        ResponseUtil responseUtil = userService.getUserById(id);
        return new ResponseEntity<>(responseUtil,responseUtil.getStatus());
    }
    @PostMapping
    public ResponseEntity<ResponseUtil> addUser(@RequestBody UserInsertCrudDTO user) {
        ResponseUtil responseUtil = userService.insertUser(user);
        return new ResponseEntity<>(responseUtil, responseUtil.getStatus());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseUtil> udpateUser(
            @PathVariable Long id,
            @RequestBody UserCrudDTO user) {
        ResponseUtil responseUtil = userService.updateUser(id,user);
        return new ResponseEntity<>(responseUtil, responseUtil.getStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }



}
