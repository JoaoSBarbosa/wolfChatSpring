package com.barbosa.wolfChat.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserInsertDTO {

    @NotBlank
    @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres.")
    private String firstName;
    @Size(max = 50, message = "O sobrenome não pode ter mais de 50 caracteres.")
    private String lastName;
    @NotBlank(message = "O nome de usuário é obrigatório e não pode estar vazio.")
    @Size(min = 3, max = 20, message = "O nome de usuário deve ter entre 3 e 20 caracteres.")
    @Pattern(regexp = "^[a-zA-Z0-9_.-]+$", message = "O nome de usuário só pode conter letras, números, pontos, traços e sublinhados.")
    private String userName;
    @NotBlank(message = "O e-mail é obrigatório e não pode estar vazio.")
    @Email(message = "Por favor, forneça um endereço de e-mail válido.")
    private String email;
    @NotBlank(message = "A senha é obrigatória e não pode estar vazia.")
    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres.")
    private String password;
    @Pattern(regexp = "^(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)$",
            message = "A URL da imagem deve ser válida e terminar com uma extensão de imagem (jpg, gif, png).")
    private String imageUri;

}
