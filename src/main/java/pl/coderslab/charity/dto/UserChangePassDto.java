package pl.coderslab.charity.dto;

import lombok.Data;
import pl.coderslab.charity.validation.ChangePassword;
import pl.coderslab.charity.validation.CheckPasswordComplexity;
import pl.coderslab.charity.validation.ConfirmPassword;

import javax.validation.constraints.NotEmpty;

@Data
@ChangePassword
@ConfirmPassword
//@CheckPasswordComplexity
public class UserChangePassDto {
    private Long id;
    @NotEmpty
    private String oldPassword;
    private String newPassword;
    @NotEmpty
    private String matchingNewPassword;

}
