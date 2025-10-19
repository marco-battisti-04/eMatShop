package it.outdoor.usermanager.configs;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            // .cors(cors -> cors.configurationSource(corsConfigurationSource())) // <-- cambia qui
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/user/register",
                    "/api/user/login",
                    "/api/user/working",
                    "/user/register",
                    "/user/login"
                ).permitAll()
                .requestMatchers("/api/user/**").authenticated()
                .anyRequest().authenticated()
            )
            .httpBasic(httpBasic -> httpBasic.disable())
            .formLogin(form -> form.disable());

        return http.build();
    }


    // @Bean
    // public CorsConfigurationSource corsConfigurationSource() {

    //     CorsConfiguration configuration = new CorsConfiguration();
    //     configuration.setAllowedOrigins(Arrays.asList("http://192.168.1.19:4200"));
    //     configuration.setAllowedMethods(Arrays.asList("*"));
    //     configuration.setAllowedHeaders(Arrays.asList("*"));
    //     configuration.setAllowCredentials(true);
    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     source.registerCorsConfiguration("/**", configuration);
    //     return source;
    // }
}