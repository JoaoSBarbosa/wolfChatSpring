package com.barbosa.wolfChat.repositories;

import com.barbosa.wolfChat.core.models.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("SELECT c FROM Chat c LEFT JOIN FETCH c.chatUsers")
    List<Chat> findAllWithUsers();

}
