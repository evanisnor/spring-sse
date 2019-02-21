package com.evanisnor.api;

import com.evanisnor.api.model.CommitReceipt;
import com.evanisnor.api.model.Message;
import com.evanisnor.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component
public class MessageHandler {

    private final EventsRepository eventsRepository;

    @Autowired
    MessageHandler(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    public Mono<ServerResponse> handleMessage(ServerRequest request) {
        return Mono.just(request)
                .flatMap(req -> req.bodyToMono(Message.class))
                .map(message -> {
                    long id = eventsRepository.getNextId();
                    eventsRepository.sendEvent(Message.toEvent(id, message));
                    return id;
                })
                .flatMap(id -> ServerResponse.ok().syncBody(new CommitReceipt(id, new Date())));
    }
}
