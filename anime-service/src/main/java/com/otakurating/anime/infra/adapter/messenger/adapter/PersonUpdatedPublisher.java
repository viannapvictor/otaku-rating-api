package com.otakurating.anime.infra.adapter.messenger.adapter;

import com.otakurating.anime.core.event.PersonUpdatedEvent;
import com.otakurating.anime.infra.adapter.messenger.dto.PersonSimpleEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PersonUpdatedPublisher {
    private static final Logger log = LoggerFactory.getLogger(PersonUpdatedPublisher.class);
    private final StreamBridge streamBridge;

    public PersonUpdatedPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @EventListener
    public void publish(PersonUpdatedEvent event) {
        PersonSimpleEventDTO dto = new PersonSimpleEventDTO(
                event.getEventId(),
                event.getTimestamp(),
                event.getPersonId()
        );
        streamBridge.send("person-updated-out-0", dto);
        log.info("Person updated event sent to stream. personId={}", event.getPersonId());
    }
}
