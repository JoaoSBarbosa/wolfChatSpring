package com.barbosa.wolfChat.repositories;

import com.barbosa.wolfChat.entities.MessageView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageViewRepository extends JpaRepository<MessageView, Long> {
}
