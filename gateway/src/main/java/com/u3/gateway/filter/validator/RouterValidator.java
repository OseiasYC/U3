package com.u3.gateway.filter.validator;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {

        public static final List<String> openApiEndpoints = List.of(
                        "/AuthorizationServer/auth/login"

        );

        public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints
                        .stream()
                        .noneMatch(uri -> {
                                return request.getURI().getPath().contains(uri);
                        });
}
