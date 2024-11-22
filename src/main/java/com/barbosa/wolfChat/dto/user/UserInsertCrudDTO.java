package com.barbosa.wolfChat.dto.user;
import com.barbosa.wolfChat.entities.User;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserInsertCrudDTO extends UserCrudDTO {

    private String password;
    @Pattern(regexp = "^(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)$",
            message = "A URL da imagem deve ser válida e terminar com uma extensão de imagem (jpg, gif, png).")
    private String imageUri;

    public UserInsertCrudDTO(String password, String imageUri) {
        this.password = password;
        this.imageUri = imageUri;
    }

    public UserInsertCrudDTO(String firstName, String lastName, String userName, String email, String imageUri, String password, String imageUri1) {
        super(firstName, lastName, userName, email, imageUri);
        this.password = password;
        this.imageUri = imageUri1;
    }

    public UserInsertCrudDTO(User user, String password, String imageUri) {
        super(user);
        this.password = password;
        this.imageUri = imageUri;
    }

    public UserInsertCrudDTO(User user) {
        super(user);
        imageUri = user.getImageUri();
    }
}
