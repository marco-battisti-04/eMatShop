package it.smartcommunitylabdhub.shopgateway.configs;

import java.time.Duration;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;

@Configuration
public class CorsConfig {

  @Bean
  public CorsWebFilter corsWebFilter() {
    CorsConfiguration config = new CorsConfiguration();
    // Usa allowedOriginPatterns se ti servono wildcard tipo https://*.example.com
    config.setAllowedOriginPatterns(List.of("http://10.36.28.137"));
    // In alternativa, se non usi wildcard:
    // config.setAllowedOrigins(List.of("https://app.example.com&quot;));

    config.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
    config.setAllowedHeaders(List.of("*"));
    config.setExposedHeaders(List.of("Content-Disposition")); // opzionale
    config.setAllowCredentials(true);                         // se ti servono cookie/Authorization
    config.setMaxAge(Duration.ofHours(1));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return new CorsWebFilter(source);
  }
}