package com.otakurating.rating.core.port.out;

import com.otakurating.rating.core.event.DomainEvent;

public interface EventPublisherPort {
    void publish(DomainEvent event);
}
