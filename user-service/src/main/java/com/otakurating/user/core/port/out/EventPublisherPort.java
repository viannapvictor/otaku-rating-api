package com.otakurating.user.core.port.out;

import com.otakurating.user.core.event.BaseEvent;

import java.util.UUID;

public interface EventPublisherPort {
    void publish(BaseEvent<UUID> event);
}