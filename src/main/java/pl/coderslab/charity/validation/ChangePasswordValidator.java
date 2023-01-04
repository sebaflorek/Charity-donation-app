package pl.coderslab.charity.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import pl.coderslab.charity.dto.UserChangePassDto;
import pl.coderslab.charity.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class ChangePasswordValidator implements ConstraintValidator<ChangePassword, UserChangePassDto> {
    private final UserService userService;

    @Override
    public void initialize(ChangePassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserChangePassDto userChangePassDto, ConstraintValidatorContext constraintValidatorContext) {
        String actualPassword = userService.findById(userChangePassDto.getId()).getPassword();
        if (!BCrypt.checkpw(userChangePassDto.getOldPassword(), actualPassword)) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("{invalid.password.old-password}")
                    .addPropertyNode("oldPassword")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
