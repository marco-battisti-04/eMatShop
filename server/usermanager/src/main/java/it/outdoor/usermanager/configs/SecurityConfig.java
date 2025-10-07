package it.outdoor.usermanager.configs;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // necessario per POST JSON
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // necessario se chiami da frontend
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/user/register",
                    "/api/user/login",
                    "/api/user/working"
                ).permitAll()
                .requestMatchers("/api/user/**").authenticated()
                .anyRequest().authenticated()
            )
            .httpBasic(httpBasic -> httpBasic.disable())
            .formLogin(form -> form.disable());

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // CorsConfiguration configuration = new CorsConfiguration();
        // configuration.setAllowedOriginPatterns(List.of("http://localhost:4200")); // per test
        // configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // configuration.setAllowedHeaders(List.of("*"));
        // configuration.setAllowCredentials(true);

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;

        // UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // source.registerCorsConfiguration("/**", configuration);
        // return source;
    }
}