package com.u3.gateway.filter;

import com.u3.gateway.filter.validator.RouterValidator;
import com.u3.gateway.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

    private final RouterValidator routerValidator;

    private final JwtUtil jwtUtil;

    public AuthenticationFilter(RouterValidator routerValidator, JwtUtil jwtUtil) {
        this.routerValidator = routerValidator;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public GlobalFilter customFilter() {
        return new AuthenticationFilter(routerValidator, jwtUtil);
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            ServerHttpRequest request = exchange.getRequest();
            if (routerValidator.isSecured.test(request)) {
                if (this.isAuthMissing(request)) {
                    return this.onError(exchange,
                            HttpStatus.UNAUTHORIZED);
                }
                String token = this.getAuthHeader(request);
                boolean valid = jwtUtil.isTokenValid(token);
                if (!valid) {
                    return this.onError(exchange, HttpStatus.UNAUTHORIZED);
                }
                this.populateRequestWithHeaders(exchange, token);
                return chain.filter(exchange);
            }
            return chain.filter(exchange);

        } catch (ExpiredJwtException e) {
            return this.onError(exchange, HttpStatus.FORBIDDEN);
        }
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {

        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);

        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {

        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {

        Claims claims = jwtUtil.getAllClaimsFromToken(token);

        exchange.getRequest().mutate().header("id", String.valueOf(claims.getSubject()))
                .header("role", String.valueOf(claims.get("role"))).build();
    }

    @Override
    public int getOrder() {

        return -1;
    }

}
