package com.barbosa.wolfChat.api.user.services;

import com.barbosa.wolfChat.api.user.dtos.UserCrudDTO;
import com.barbosa.wolfChat.api.user.dtos.UserInsertCrudDTO;
import com.barbosa.wolfChat.models.entities.User;
import com.barbosa.wolfChat.repositories.ChatUserRepository;
import com.barbosa.wolfChat.repositories.UserRepository;
import com.barbosa.wolfChat.utils.CommonUtil.CommunUtils;
import com.barbosa.wolfChat.utils.model.ResponseUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    ChatUserRepository chatUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseUtil insertUser(UserInsertCrudDTO user) {
        User entity = new User();

        copyDTOToEntity(user, entity);
        entity = userRepository.save(entity);
        return ResponseUtil
                .builder()
                .data(entity)
                .status(HttpStatus.CREATED)
                .message("Registro de usuario cadastrado com sucesso!")
                .sendDateTime(CommunUtils.getDateTime())
                .build();

    }

    @Transactional(readOnly = true)
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public ResponseUtil getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi localizado registro de Usuairo com o id informado"));
        return ResponseUtil
                .builder()
                .data(user)
                .status(HttpStatus.OK)
                .message("Registro de usuario localizado com sucesso!")
                .sendDateTime(CommunUtils.getDateTime())
                .build();
    }

    @Transactional
    public ResponseUtil updateUser(Long id, UserCrudDTO dto) {


        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não existe registro de colaborador com o id informado: " + id));

        User entity = new User();

        copyDTOToEntity(dto, entity);

        if (dto.getUserName() != null) user.setUserName(dto.getUserName());
        if (dto.getFirstName() != null) user.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) user.setLastName(dto.getLastName());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getImageUri() != null) user.setImageUri(dto.getImageUri());

        user.setUpdateIn(LocalDateTime.now());
        user = userRepository.save(user);

        return ResponseUtil
                .builder()
                .data(user)
                .status(HttpStatus.CREATED)
                .message("Registro de usuario atualizado com sucesso!")
                .sendDateTime(CommunUtils.getDateTime())
                .build();


    }

    @Transactional
    public void deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }



    private void copyDTOToEntity(UserCrudDTO dto, User user) {
        if (dto.getUserName() != null) user.setUserName(dto.getUserName());
        if (dto.getFirstName() != null) user.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) user.setLastName(dto.getLastName());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getImageUri() != null) user.setImageUri(dto.getImageUri());

        user.setCreateIn( LocalDateTime.now());
        if( dto instanceof UserInsertCrudDTO insertCrudDTO) {
            if(insertCrudDTO.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(insertCrudDTO.getPassword()));
            }
        }
    }
}
