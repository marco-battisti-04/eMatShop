package it.smartcommunitylabdhub.shopgateway.configs;

import java.time.Duration;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import java.util.Collections;
import java.util.Arrays;

@Configuration
public class CorsConfig {

  @Bean
  public CorsWebFilter corsWebFilter() {

    final CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(Collections.singletonList("*"));
    config.setAllowedMethods(Arrays.asList("*"));
    config.addAllowedHeader("*");

    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);

    return new CorsWebFilter(source);
  } 
}