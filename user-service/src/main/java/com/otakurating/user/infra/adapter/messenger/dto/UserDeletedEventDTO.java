package com.otakurating.user.infra.adapter.messenger.dto;

import java.time.Instant;
import java.util.UUID;

public class UserDeletedEventDTO {
    private UUID eventId;
    private Instant timestamp;
    private String userId;
    private String username;

    public UserDeletedEventDTO() {
    }

    public UserDeletedEventDTO(UUID eventId, Instant timestamp, String userId, String username) {
        this.eventId = eventId;
        this.timestamp = timestamp;
        this.userId = userId;
        this.username = username;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}