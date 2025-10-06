// package it.outdoor.usermanager.configs;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// @Configuration
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable()) // necessario per POST JSON
//             .cors(cors -> {}) // necessario se chiami da frontend
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/api/user/register", "/api/user/working   ").permitAll()
//                 .requestMatchers("/api/user/**").authenticated()
//                 .anyRequest().authenticated()
//             )
//             .httpBasic(httpBasic -> httpBasic.disable())
//             .formLogin(form -> form.disable());

//         return http.build();
//     }

//     // @Bean
//     // public CorsConfigurationSource corsConfigurationSource() {
//     //     CorsConfiguration configuration = new CorsConfiguration();
//     //     configuration.addAllowedOriginPattern("*"); // apri tutto per test
//     //     configuration.addAllowedMethod("*");
//     //     configuration.addAllowedHeader("*");
//     //     configuration.setAllowCredentials(true);

//     //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//     //     source.registerCorsConfiguration("/**", configuration);
//     //     return source;
//     // }


// }
