package com.evanisnor.api;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class PingHandler {

    public Mono<ServerResponse> handlePing(ServerRequest request) {
        return Mono.just(request).flatMap(req -> ServerResponse.ok().build());
    }

}

