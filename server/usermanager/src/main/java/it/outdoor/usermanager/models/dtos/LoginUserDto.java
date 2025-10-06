package it.outdoor.usermanager.models.dtos;

import lombok.Getter;

@Getter
public class LoginUserDto {
    private String email;
    private String password;
}