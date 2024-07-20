package com.barbosa.wolfChat.services;

import com.barbosa.wolfChat.entities.User;
import com.barbosa.wolfChat.repositories.UserRepository;
import com.barbosa.wolfChat.utils.CommonUtil.CommunUtils;
import com.barbosa.wolfChat.utils.model.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseUtil insertUser(User user) {
        User savedUser = userRepository.save(user);
        return ResponseUtil
                .builder()
                .data(savedUser)
                .status(HttpStatus.CREATED)
                .message("Registro de usuario cadastrado com sucesso!")
                .sendDateTime(CommunUtils.getDateTime())
                .build();

    }
}
