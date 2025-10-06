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

import it.outdoor.usermanager.models.dtos.LoginUserDto;
import it.outdoor.usermanager.models.dtos.RegisterUserDto;
import it.outdoor.usermanager.repositories.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.outdoor.usermanager.services.AuthenticationService;
import it.outdoor.usermanager.services.JwtService;
import it.outdoor.usermanager.responses.LoginResponse;

@RestController
@RequestMapping("/api/user")
public class UserController {

    // private final UserService userService;
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public UserController(JwtService jwtService, AuthenticationService authenticationService) {//, UserService userService) {
        // this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
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
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {

        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        Optional<User> authenticatedUser = authenticationService.authenticate(loginUserDto);

        User user = authenticatedUser.orElseThrow();
        String jwtToken = jwtService.generateToken(user);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }


    // public String login(@RequestBody requestBody) {



    //     return "";

    //     // boolean isMatch = passwordService.verifyPassword(password, storedHashedPassword);
    //     // return isMatch ? "Login successful!" : "Invalid credentials.";
    // }

}
