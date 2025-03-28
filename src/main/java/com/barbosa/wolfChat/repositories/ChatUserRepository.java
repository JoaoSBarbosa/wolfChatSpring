package com.barbosa.wolfChat.repositories;

import com.barbosa.wolfChat.core.models.entities.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {

    @Query("SELECT cu FROM ChatUser cu WHERE cu.chat.chatId = :chatId AND cu.user.userId = :userId")
    Optional<ChatUser> findByChatIdAndUserId(@Param("chatId") Long chatId, @Param("userId") Long userId);

//    Optional<ChatUser> findByChatIdAndUserId(Long chatiD, Long chatId);


    @Query("SELECT ctu FROM ChatUser ctu WHERE ctu.chat.chatId = :chatId ORDER BY ctu.joinedAt ASC")
    List<ChatUser> findAdminsByChatId(@Param("chatId") Long chatId);


    @Query("SELECT ctu FROM ChatUser ctu WHERE ctu.chat.chatId = :chatId ORDER BY ctu.joinedAt ASC")
    Optional<ChatUser> findOldMemberByChatId(Long chatId);

//    Long countByChatId(Long chatId);

}
