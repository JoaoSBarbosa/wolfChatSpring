package com.barbosa.wolfChat.api.message.dtos;

import com.barbosa.wolfChat.api.user.dtos.UserMessageDTO;
import com.barbosa.wolfChat.core.models.entities.Message;
import com.barbosa.wolfChat.core.models.entities.MessageView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {

    private Long msgId;
    private String content;
    private LocalDateTime timestamp;
    private UserMessageDTO sender;
    private Set<UserMessageDTO> viewedBy = new HashSet<>();

    public MessageDTO(Message message) {
        this.msgId = message.getMsgId();
        this.content = message.getContent();
        this.timestamp = message.getTimestamp();

        if (message.getSender() != null) {
            this.sender = new UserMessageDTO(message.getSender());
        }
    }

    public MessageDTO(Message message, Set<MessageView> viewedBy) {
        this(message);
        if (viewedBy != null) {
            viewedBy.forEach(view -> this.viewedBy.add(new UserMessageDTO(view.getViewer())));
        }
    }

    // ✅ MÉTODO PADRÃO FACTORY
    public static MessageDTO fromEntity(Message message) {
        return MessageDTO.builder()
                .msgId(message.getMsgId())
                .content(message.getContent())
                .timestamp(message.getTimestamp())
                .sender(new UserMessageDTO(message.getSender()))
                .viewedBy(
                        message.getViewedBy() != null
                                ? message.getViewedBy().stream()
                                .map(view -> new UserMessageDTO(view.getViewer()))
                                .collect(Collectors.toSet())
                                : new HashSet<>()
                )
                .build();
    }
}
