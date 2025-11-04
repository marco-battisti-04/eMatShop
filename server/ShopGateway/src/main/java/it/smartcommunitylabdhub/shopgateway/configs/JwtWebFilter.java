package it.smartcommunitylabdhub.shopgateway.configs;
import it.smartcommunitylabdhub.shopgateway.services.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JwtWebFilter implements WebFilter {

    private final JWTService jwtService;

    @Autowired
    public JwtWebFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String token = getToken(exchange.getRequest());

        if (token != null && jwtService.isTokenValid(token)) {
            String username = jwtService.extractEmail(token);
            String userId = jwtService.extractUserID(token);
            System.out.println("USER ID: " + userId);
            System.out.println(username);
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();

            if (path.indexOf("user/me") == -1) {

                int index = path.indexOf("/me");

                if (index != -1) {
                    String newPath = path.substring(0, index) + "/"+ userId + path.substring(index + 3);
                    ServerHttpRequest newRequest = request.mutate()
                    .path(newPath)
                    .method(request.getMethod())
                    .build();
                    exchange = exchange.mutate().request(newRequest).build();

                    System.out.println(exchange.getRequest().getMethod());
                    System.out.println("NEW PATH: " + newPath);
                }
            }

            Authentication auth = new UsernamePasswordAuthenticationToken(username, null, List.of());
            return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(auth));
        }
        return chain.filter(exchange);
    }

    private String getToken(ServerHttpRequest request) {
        String authHeader = request.getHeaders().getFirst("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}