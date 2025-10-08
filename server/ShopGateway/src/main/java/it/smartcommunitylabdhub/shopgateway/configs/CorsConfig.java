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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
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
    public CorsFilter corsFilter() {
        // Create a new CorsConfiguration object
        CorsConfiguration config = new CorsConfiguration();

        // Allow credentials like cookies, authorization headers, or TLS client
        // certificates
        config.setAllowCredentials(true);

        // Specify the list of allowed origins that can access the application
        // Your front-end URLs only
        config.setAllowedOrigins(Arrays.asList("http://10.30.206.249:4200"));

        // Allow Standard headers
        config.setAllowedHeaders(Arrays.asList("Authorization", // Authorization → for tokens (JWT, OAuth, etc.)
            "Cache-Control", // Cache-Control → browser cache behavior
            "Content-Type", // Content-Type → like application/json
            "X-Requested-With", // X-Requested-With → for Ajax requests (especially legacy)
            "Accept", // Accept → what response formats are acceptable (application/json, etc.)
            "Origin" // Origin → original domain of request (used internally)
        ));

        // Specify the HTTP methods that are allowed
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD"));

        // Create a source that maps URL patterns to CORS configurations
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // Register the CORS configuration to apply to all paths (/** means all
        // endpoints)
        source.registerCorsConfiguration("/**", config);

        // Return the CorsFilter with the configured source
        return new CorsFilter(source);
    }
}