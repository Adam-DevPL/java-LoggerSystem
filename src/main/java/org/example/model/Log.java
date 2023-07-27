package org.example.model;

import org.example.enums.AccessType;

import java.time.LocalDateTime;

public class Log {
    //mod static final
    //Instant
    private final LocalDateTime timestamp;
    private final User creator;
    private final String text;
    private final AccessType type;

    public Log(LocalDateTime timestamp, User creator, String text, AccessType type) {
        this.timestamp = timestamp;
        this.creator = creator;
        this.text = text;
        this.type = type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public User getCreator() {
        return creator;
    }

    public String getText() {
        return text;
    }

    public AccessType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Log{" +
                "timestamp=" + timestamp +
                ", creator=" + creator +
                ", text='" + text + '\'' +
                ", type=" + type +
                '}';
    }
}
