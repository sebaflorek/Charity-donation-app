package pl.coderslab.charity.validation;

import lombok.RequiredArgsConstructor;
import pl.coderslab.charity.dto.UserEditDto;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueUsernameEditValidator implements ConstraintValidator<UniqueUsername, UserEditDto> {
    private final UserService userService;

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserEditDto userEditDto, ConstraintValidatorContext constraintValidatorContext) {
        User checkUser = userService.findByUsername(userEditDto.getUsername());
        if (checkUser != null && !checkUser.getId().equals(userEditDto.getId())) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("{invalid.username.username-unique}")
                    .addPropertyNode("username")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
