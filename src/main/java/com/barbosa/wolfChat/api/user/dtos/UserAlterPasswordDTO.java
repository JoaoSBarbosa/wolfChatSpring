package com.barbosa.wolfChat.api.user.dtos;

import com.barbosa.wolfChat.utils.validations.PasswordsMatch;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@PasswordsMatch(message = "A senha e a confirmação da senha devem ser iguais.")
public class UserAlterPasswordDTO {

    @NotBlank(message = "A senha não pode estar vazia.")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
    private String password;

    @NotBlank(message = "A confirmação da senha não pode estar vazia.")
    @Size(min = 8, message = "A confirmação da senha deve ter no mínimo 8 caracteres.")
    private String confirmPassword;

}
