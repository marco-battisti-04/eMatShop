package it.outdoor.usermanager.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.outdoor.usermanager.services.UserService;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import it.outdoor.usermanager.models.User;
import it.outdoor.usermanager.models.UserDTO;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/working")
    public String hello(){
        return "working";
    }

    /**
     * Register endpoint
     *  input: {
     *   "name": "string",
     *   "surname": "string",
     *   "email": "string",
     *   "password": "string"
     *  }
     *
     *  output: {
     *   "id": "string"
     *   "name": "string",
     *   "surname": "string",
     *   "email": "string",
     *  }
     *
     * @return
     */
    // @PostMapping("/register")
    @PostMapping("/register")
    public UserDTO register(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }


    // @PostMapping("/login")
    // public String login(@RequestBody requestBody) {

        

    //     return "";

    //     // boolean isMatch = passwordService.verifyPassword(password, storedHashedPassword);
    //     // return isMatch ? "Login successful!" : "Invalid credentials.";
    // }

}
