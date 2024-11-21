package com.barbosa.wolfChat.dto.chat;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class CreateChatRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private List<Long> usersIds = new ArrayList<>();

    public CreateChatRequestDTO() {}
    public CreateChatRequestDTO(Set<Long> usersIds) {}
}
