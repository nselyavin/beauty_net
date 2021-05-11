package ru.fume.beautynet.payload.request;

import lombok.Data;
import ru.fume.beautynet.annotations.PasswordMatches;
import ru.fume.beautynet.annotations.ValidEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@PasswordMatches
public class SignupRequest {
    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    @ValidEmail
    private String email;
    @NotEmpty(message = "Please enter your name")
    private String firstname;
    @NotEmpty(message = "Please enter your lastname")
    private String lastname;
    @NotEmpty(message = "Please enter your username")
    private String username;
    @NotEmpty(message = "Please enter your password")
    @Size(min = 6)
    private String password;
    @NotEmpty(message = "Please repeat password")
    private String confirmPassword;


}
