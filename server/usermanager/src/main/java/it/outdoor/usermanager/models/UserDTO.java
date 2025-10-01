package it.outdoor.usermanager.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "users")
public class UserDTO {

    @Id
    private String userId;
    private String name;
    private String surname;
    private String email;

    @JsonIgnore
    private String password;
}
