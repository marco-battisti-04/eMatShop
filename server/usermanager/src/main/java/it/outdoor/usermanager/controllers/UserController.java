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
        // funzione per la registrazione di un nuovo utente

        // controlla se l'utente è gia presente (il controllo viene fatto tramite email)
        Optional<User> user = authenticationService.find(registerUserDto.getEmail());

        // se l'utente esiste restituisce un errore
        if (user.isPresent()) {
            // utente gia presetne
            return ResponseEntity.badRequest().body("User already exists");
        } else {
            // crea l'utente con le credenziali passate (vedi /** */ presente sopra la funzione di cosa viene passato in ingresso e cosa viene restituito)
            User registeredUser = authenticationService.signup(registerUserDto);

            // restituisce l'utente creato
            return ResponseEntity.ok(registeredUser);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {

        // un'utente optional (non è sicuro che all'interno della variable sia effettivametne un'utente, quindi non puo essere manipolato direttamente)
        // cerca di fare l'autenticazione con le credenziali passate
        Optional<User> authenticatedUser = authenticationService.authenticate(loginUserDto);

        // converte l'optional in un utente effettivo, dando un'errore se non è presente
        User user = authenticatedUser.orElseThrow();

        // genera il token per l'utente
        String jwtToken = jwtService.generateToken(user);

        // crea la risposta (token, scadenza del token)
        LoginResponse loginResponse = new LoginResponse();

        // inserisce gli attributi
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        // restituisce la risposta
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/me")
    public ResponseEntity<User> pioppo() {
        // recupera l'utente corrente
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // lo converte in utente con un cast di tipo User
        User currentUser = (User)authentication.getPrincipal();

        // restituisce l'utente
        return ResponseEntity.ok(currentUser);
    }
}
