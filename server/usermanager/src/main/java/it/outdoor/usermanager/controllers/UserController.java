package it.outdoor.usermanager.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import brave.Response;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final UserService userService;

    public UserController(JwtService jwtService, AuthenticationService authenticationService, UserService userService) {//, UserService userService) {
        // this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.userService = userService;
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
    public ResponseEntity<?> register(@RequestBody RegisterUserDto registerUserDto) {

        Optional<User> user = authenticationService.find(registerUserDto.getEmail());

        if (user.isPresent()) {
            return ResponseEntity.badRequest().body("User already exists");
        } else {
            User registeredUser = authenticationService.signup(registerUserDto);
            return ResponseEntity.ok(registeredUser);
        }
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

    @GetMapping("/me")
    public ResponseEntity<User> pioppo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User)authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }
}
