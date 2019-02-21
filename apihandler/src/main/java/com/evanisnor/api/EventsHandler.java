package com.evanisnor.api;

import com.evanisnor.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class EventsHandler {

    private final EventsRepository eventsRepository;

    @Autowired
    EventsHandler(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    public Mono<ServerResponse> openStream(ServerRequest request) {
        return Mono.just(request)
                .map(r -> r.pathVariable("recipient"))
                .map(recipientId -> {
                    List<String> h = request.headers().header("Last-Event-ID");
                    int lastEventId = h.isEmpty() ? 0 : Integer.parseInt(h.get(0));
                    return eventsRepository.subscribe(recipientId, lastEventId);
                })
                .map(flux -> flux.map(event -> ServerSentEvent.builder()
                        .id(Long.toString(event.getId()))
                        .data(event)
                        .build()))
                .flatMap(serverSentEventFlux -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_EVENT_STREAM)
                        .body(serverSentEventFlux::subscribe, ServerSentEvent.class));
    }
}
