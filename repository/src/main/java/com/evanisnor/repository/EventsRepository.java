package com.evanisnor.repository;

import com.evanisnor.repository.model.Event;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EventsRepository {

    private final AtomicLong currentId = new AtomicLong();
    private final ConcurrentHashMap<String, List<Event>> history = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, FluxSink<Event>> sinkMap = new ConcurrentHashMap<>();

    public long getNextId() {
        return currentId.incrementAndGet();
    }

    public void sendEvent(Event event) {
        String recipientId = event.getRecipientId();
        if (!history.containsKey(recipientId)) {
            history.put(recipientId, Collections.synchronizedList(new LinkedList<>()));
        }
        history.get(recipientId).add(event);
        deliver(event);
    }

    public Flux<Event> subscribe(String recipientId, int lastEventId) {
        return Flux.create(sink -> {
            sinkMap.put(recipientId, sink);
            flushQueue(recipientId, lastEventId);
        });
    }


    private void flushQueue(String recipientId, int lastEventId) {
        if (history.containsKey(recipientId)) {
            List<Event> recipientHistory = history.get(recipientId);
            for (int i = lastEventId; i < recipientHistory.size(); i++) {
                deliver(recipientHistory.get(i));
            }
        }
    }

    private void deliver(Event event) {
        if (event == null) {
            return;
        }

        String recipientId = event.getRecipientId();
        if (sinkMap.containsKey(recipientId)) {
            sinkMap.get(recipientId).next(event);
        }
    }
}
