package com.barbosa.wolfChat.repositories;

import com.barbosa.wolfChat.entities.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {
}
