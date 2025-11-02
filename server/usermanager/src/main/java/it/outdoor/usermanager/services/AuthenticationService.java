package it.outdoor.usermanager.services;

import it.outdoor.usermanager.models.dtos.LoginUserDto;
import it.outdoor.usermanager.models.dtos.RegisterUserDto;
import it.outdoor.usermanager.models.User;
import it.outdoor.usermanager.repositories.UserRepository;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        // User user = new User()
        //         .setFullName(input.getFullName())
        //         .setEmail(input.getEmail())
        //         .setPassword(passwordEncoder.encode(input.getPassword()));
        User user = new User();
        user.setName(input.getName());
        user.setSurname(input.getSurname());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));


        return userRepository.save(user);
    }

    public Optional<User> find(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail());
    }
}