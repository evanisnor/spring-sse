package com.evanisnor.repository.model;

public class Event {

    private final long id;
    private final Person sender;
    private final String recipientId;
    private final String contentType;
    private final Object content;

    public Event(long id, Person sender, String recipientId, String contentType, Object content) {
        this.id = id;
        this.sender = sender;
        this.recipientId = recipientId;
        this.contentType = contentType;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public Person getSender() {
        return sender;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public String getContentType() {
        return contentType;
    }

    public Object getContent() {
        return content;
    }
}
