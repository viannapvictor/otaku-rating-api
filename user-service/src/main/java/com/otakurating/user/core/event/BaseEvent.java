package com.otakurating.user.core.event;

import java.time.Instant;

public abstract class BaseEvent<I> {
    private final I eventId;
    private final Instant timestamp;

    public BaseEvent(I eventId) {
        this.eventId = eventId;
        this.timestamp = Instant.now();
    }

    public I getEventId() {
        return eventId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}