package com.barbosa.wolfChat.utils.validations;

import com.barbosa.wolfChat.api.user.dtos.UserAlterPasswordDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, UserAlterPasswordDTO> {

    @Override
    public boolean isValid(UserAlterPasswordDTO dto, ConstraintValidatorContext constraintValidatorContext) {
       if(dto.getPassword() == null || dto.getConfirmPassword() == null) return false;

       return dto.getPassword().equals(dto.getConfirmPassword());
    }
}
