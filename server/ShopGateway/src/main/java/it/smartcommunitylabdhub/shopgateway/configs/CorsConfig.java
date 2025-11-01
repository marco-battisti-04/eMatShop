package it.smartcommunitylabdhub.shopgateway.configs;

// import java.time.Duration;
// import java.util.List;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
// import org.springframework.web.cors.reactive.CorsWebFilter;
// import java.util.Collections;
// import java.util.Arrays;

// @Configuration
// public class CorsConfig {

//   @Bean
//   public CorsWebFilter corsWebFilter() {

//     final CorsConfiguration config = new CorsConfiguration();
//     config.setAllowedOrigins(Collections.singletonList("*"));
//     config.setAllowedMethods(Arrays.asList("*"));
//     config.addAllowedHeader("*");

//     final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//     source.registerCorsConfiguration("/**", config);

//     return new CorsWebFilter(source);
//   } 
// }


import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * WebConfig class is responsible for configuring CORS settings across the
 * application.
 *
 * CORS (Cross-Origin Resource Sharing) allows a web application running on one
 * domain to access resources from another domain.
 *
 * CORS is like a firewall on your backend that controls which frontend
 * applications are allowed to talk to it.
 */
@Configuration
public class CorsConfig {

    /**
     * Defines a CorsFilter bean that applies the CORS configuration globally.
     *
     * @return CorsFilter that applies CORS rules to all incoming HTTP requests
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        
        config.setAllowedOrigins(List.of("http://192.168.1.19:4200"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Content-Type", "Authorization"));
        config.setAllowCredentials(true); // se usi cookie o autenticazione via sessione

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}