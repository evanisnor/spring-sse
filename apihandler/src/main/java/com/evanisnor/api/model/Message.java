package com.evanisnor.api.model;


import com.evanisnor.repository.model.Event;

import java.util.Date;
import java.util.Objects;

public class Message {

    private Person sender;
    private String recipientId;
    private String text;
    private Date sentOn;

    public Message() {
    }

    public Person getSender() {
        return sender;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public String getText() {
        return text;
    }

    public Date getSentOn() {
        return sentOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(sender, message.sender) &&
                Objects.equals(recipientId, message.recipientId) &&
                Objects.equals(text, message.text) &&
                Objects.equals(sentOn, message.sentOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, recipientId, text, sentOn);
    }

    public static Event toEvent(long id, Message message) {
        return new Event(id,
                new com.evanisnor.repository.model.Person(message.sender.getId(), message.sender.getName()),
                message.recipientId,
                com.evanisnor.repository.model.Message.TYPE,
                message);
    }
}
