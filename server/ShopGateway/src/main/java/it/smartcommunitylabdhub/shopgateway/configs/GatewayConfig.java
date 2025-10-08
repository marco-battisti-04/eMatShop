package it.smartcommunitylabdhub.shopgateway.configs;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class GatewayConfig {

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("catalog_root", r -> r
						.path("/catalog")
						.filters(f -> f
								.circuitBreaker(config -> config
										.setName("catalog")
										.setFallbackUri("forward:/fallback/catalog"))
								.setPath("/api/products"))
						.uri("lb://catalog"))
				.route("catalog_all", r -> r
						.path("/catalog/**")
						.filters(f -> f.circuitBreaker(config -> config
								.setName("catalog")
								.setFallbackUri("forward:/fallback/catalog"))
								.stripPrefix(1).prefixPath("/api/products"))
						.uri("lb://catalog"))

				// purchase
				.route("purchase_root", r -> r
						.path("/purchase")
						.filters(f -> f.circuitBreaker(config -> config
								.setName("purchase")
								.setFallbackUri("forward:/fallback/purchase"))
								.setPath("/api/orders"))
						.uri("lb://purchase"))

				.route("purchase_all", r -> r
						.path("/purchase/**")
						.filters(f -> f.circuitBreaker(config -> config
								.setName("purchase")
								.setFallbackUri("forward:/fallback/purchase"))
								.stripPrefix(1).prefixPath("/api/orders"))
						.uri("lb://purchase"))
				.route("cart_root", r -> r
						.path("/cart")
						.filters(f -> f.setPath("/api/carts"))
						.uri("lb://purchase"))
				.route("cart_all", r -> r
						.path("/cart/**")
						.filters(f -> f.stripPrefix(1).prefixPath("/api/carts"))
						.uri("lb://purchase"))

				// sezione USER
				.route("usermanager_root", r -> r
						.path("/user")
						.filters(f -> f.setPath("/api/user"))
						.uri("lb://usermanager"))
				.route("usermanager_all", r -> r
						.path("/user/**")
						.filters(f -> f.stripPrefix(1).prefixPath("/api/user"))
						.uri("lb://usermanager"))
				.build();
	}

    // @Bean
    // public CorsConfigurationSource corsConfigurationSource() {
    //     CorsConfiguration configuration = new CorsConfiguration();
    //     configuration.setAllowedOriginPatterns(List.of("*")); // per test
    //     configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    //     configuration.setAllowedHeaders(List.of("*"));
    //     configuration.setAllowCredentials(true);

    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     source.registerCorsConfiguration("/**", configuration);
    //     return source;
    // }
}

