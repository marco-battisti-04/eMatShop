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

    // declaring the hash service

    //    List products: /api/products
    public List<UserDTO> getAllProducts() {
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

    //    Get product by Id: /api/products/{id})
    // public Optional<ProductDTO> getProductById(String id) {
    //     return userRepository.findById(id).map(product ->
    //             new ProductDTO(
    //                     product.getId(),
    //                     product.getCode(),
    //                     product.getTitle(),
    //                     product.getCategory(),
    //                     product.getDescription(),
    //                     product.getPrice(),
    //                     product.getAvailability()
    //     ));
    // }

    //    Search by category: /api/products/category/{category}
    // public List<ProductDTO> getProductsByCategory(String category) {
    //     return userRepository.findByCategory(category)
    //             .stream().map(product ->
    //                     new ProductDTO(
    //                             product.getId(),
    //                             product.getCode(),
    //                             product.getTitle(),
    //                             product.getCategory(),
    //                             product.getDescription(),
    //                             product.getPrice(),
    //                             product.getAvailability()))
    //             .toList();
    // }

    //Create product: POST /api/products
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


    public String hashPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

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