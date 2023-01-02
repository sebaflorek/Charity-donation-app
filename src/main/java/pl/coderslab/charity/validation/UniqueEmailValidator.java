package pl.coderslab.charity.validation;

import lombok.RequiredArgsConstructor;
import pl.coderslab.charity.dto.UserCreateDto;
import pl.coderslab.charity.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, UserCreateDto> {
    private final UserService userService;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserCreateDto userCreateDto, ConstraintValidatorContext constraintValidatorContext) {
        if (userService.findByUserEmail(userCreateDto.getEmail()) != null) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("{invalid.email.email-unique}")
                    .addPropertyNode("email")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
