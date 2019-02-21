package com.evanisnor.booter;

import com.evanisnor.api.EventsHandler;
import com.evanisnor.api.MessageHandler;
import com.evanisnor.api.PingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Routes {

    private final PingHandler pingHandler;
    private final MessageHandler messageHandler;
    private final EventsHandler eventsHandler;

    @Autowired
    Routes(PingHandler pingHandler, MessageHandler messageHandler, EventsHandler eventsHandler) {
        this.pingHandler = pingHandler;
        this.messageHandler = messageHandler;
        this.eventsHandler = eventsHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> getRoutes() {
        return route(GET("/ping"), pingHandler::handlePing)
                .and(route(POST("/message"), messageHandler::handleMessage))
                .and(route(GET("/events/{recipient}"), eventsHandler::openStream));
    }
}
