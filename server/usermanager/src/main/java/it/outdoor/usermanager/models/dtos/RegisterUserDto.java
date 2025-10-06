package it.outdoor.usermanager.models.dtos;

import lombok.Getter;

@Getter
public class RegisterUserDto {
    private String name;
    private String surname;
    private String email;

    private String password;
}