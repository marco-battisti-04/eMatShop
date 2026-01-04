package it.outdoor.usermanager.responses;

import lombok.Getter;
import lombok.Setter;

@Getter // decoratori
@Setter
public class LoginResponse {
    private String token;

    private long expiresIn;

 // Getters and setters...
}