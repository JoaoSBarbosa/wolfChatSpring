package com.barbosa.wolfChat.api.user.services;

import com.barbosa.wolfChat.api.user.dtos.UserCrudDTO;
import com.barbosa.wolfChat.api.user.dtos.UserInsertCrudDTO;
import com.barbosa.wolfChat.api.user.dtos.UserRequest;
import com.barbosa.wolfChat.core.models.entities.User;
import com.barbosa.wolfChat.utils.model.ResponseUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    List<UserRequest> getUsers();
    ResponseUtil insertUser(UserInsertCrudDTO user);
    Page<User> getAllUsers(Pageable pageable);
    ResponseUtil getUserById(Long id);
    ResponseUtil updateUser(Long id, UserCrudDTO dto);
    void deleteUser(Long id);
}
