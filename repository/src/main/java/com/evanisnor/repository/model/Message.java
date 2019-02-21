package com.evanisnor.repository.model;

import java.util.Date;

public class Message {

    public static final String TYPE = "Message";

    private final String text;
    private final Date sentOn;

    public Message(String text, Date sentOn) {
        this.text = text;
        this.sentOn = sentOn;
    }

    public String getText() {
        return text;
    }

    public Date getSentOn() {
        return sentOn;
    }
}
