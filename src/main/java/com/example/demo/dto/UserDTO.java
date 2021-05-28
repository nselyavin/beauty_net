package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
public class UserDTO {
    @NotEmpty
    private String username;
    @NotEmpty
    private String name;
    private String lastname;
    @Email
    private String email;
    private String password;
}
