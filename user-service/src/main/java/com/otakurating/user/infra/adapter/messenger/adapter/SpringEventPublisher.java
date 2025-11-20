package com.otakurating.user.infra.adapter.messenger.adapter;

import com.otakurating.user.core.event.BaseEvent;
import com.otakurating.user.core.port.out.EventPublisherPort;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SpringEventPublisher implements EventPublisherPort {
    private final ApplicationEventPublisher publisher;

    public SpringEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(BaseEvent<UUID> event) {
        publisher.publishEvent(event);
    }
}