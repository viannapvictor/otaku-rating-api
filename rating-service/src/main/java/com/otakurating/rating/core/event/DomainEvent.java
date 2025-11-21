package com.otakurating.rating.core.event;

import java.time.Instant;
import java.util.UUID;

public abstract class DomainEvent {
    private final UUID eventId;
    private final Instant timestamp;

    public DomainEvent() {
        this.eventId = UUID.randomUUID();
        this.timestamp = Instant.now();
    }

    public UUID getEventId() {
        return eventId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
