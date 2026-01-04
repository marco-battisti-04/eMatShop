package it.outdoor.usermanager.services;

import it.outdoor.usermanager.models.User;
import it.outdoor.usermanager.models.UserDTO;
import it.outdoor.usermanager.repositories.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ritorna tutti gli utenti sottoforma di lista
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(user ->
            new UserDTO(
                user.getUserId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getPassword()
            )
        ).toList();
    }

    // funzione per creare un nuovo utente
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(hashPassword(userDTO.getPassword()));

        userRepository.save(user);

        userDTO.setUserId(user.getUserId());
        return userDTO;
    }


    // fa l'hash della password per la creazione
    public String hashPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    // controlla la password facendo un match tra gli hash
    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }


    //    Change availability: PUT /api/products/{id}/availability/{value}
    // public ProductDTO changeAvailability(String id, Integer value) {
    //     Product product = userRepository.findById(id).orElse(null);
    //     if(product == null) {
    //         return null;
    //     }

    //     product.setAvailability(value);
    //     userRepository.save(product);
    //     ProductDTO productDTO = new ProductDTO();
    //     productDTO.setId(product.getId());
    //     productDTO.setCode(product.getCode());
    //     productDTO.setTitle(product.getTitle());
    //     productDTO.setCategory(product.getCategory());
    //     productDTO.setDescription(product.getDescription());
    //     productDTO.setPrice(product.getPrice());
    //     productDTO.setAvailability(product.getAvailability());
    //     return productDTO;
    // }

}