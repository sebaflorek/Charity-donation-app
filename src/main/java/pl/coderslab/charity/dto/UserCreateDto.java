package pl.coderslab.charity.dto;

import lombok.Data;
import pl.coderslab.charity.validation.CheckPasswordComplexity;
import pl.coderslab.charity.validation.ConfirmPassword;
import pl.coderslab.charity.validation.UniqueEmail;
import pl.coderslab.charity.validation.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@UniqueEmail
@UniqueUsername
@ConfirmPassword
@CheckPasswordComplexity
public class UserCreateDto {

    @NotBlank
    @Size(min = 3, max = 20, message = "{invalid.username.username-length}")
    private String username;

    //@NotBlank
    private String name;

    //@NotBlank
    private String surname;

    @NotEmpty
    @Email
    private String email;

    //@NotEmpty
    private String password;

    @NotEmpty
    private String matchingPassword;
}
