package com.barbosa.wolfChat.api.user.dtos;
import com.barbosa.wolfChat.core.models.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UserCrudDTO {

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
    @Pattern(regexp = "^(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)$",
            message = "A URL da imagem deve ser válida e terminar com uma extensão de imagem (jpg, gif, png).")
    private String imageUri;

    public UserCrudDTO(){}

    public UserCrudDTO(String firstName, String lastName, String userName, String email, String imageUri) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.imageUri = imageUri;
    }

    public UserCrudDTO(User user) {
        firstName = user.getFirstName();
        lastName = user.getLastName();
        userName = user.getUserName();
        email = user.getEmail();
        imageUri = user.getImageUri();
    }
}
