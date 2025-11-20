package com.otakurating.anime.core.port.out;

import com.otakurating.anime.core.event.DomainEvent;

public interface EventPublisherPort {
    void publish(DomainEvent event);
}
