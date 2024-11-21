package com.barbosa.wolfChat.services;

import com.barbosa.wolfChat.entities.User;
import com.barbosa.wolfChat.repositories.UserRepository;
import com.barbosa.wolfChat.utils.CommonUtil.CommunUtils;
import com.barbosa.wolfChat.utils.model.ResponseUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional(readOnly = true)
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional( readOnly = true)
    public ResponseUtil getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Não foi localizado registro de Usuairo com o id informado"));
        return ResponseUtil
                .builder()
                .data(user)
                .status(HttpStatus.OK)
                .message("Registro de usuario localizado com sucesso!")
                .sendDateTime(CommunUtils.getDateTime())
                .build();
    }
}
