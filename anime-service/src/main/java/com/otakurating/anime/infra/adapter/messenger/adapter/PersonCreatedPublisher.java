package com.otakurating.anime.infra.adapter.messenger.adapter;

import com.otakurating.anime.core.event.PersonCreatedEvent;
import com.otakurating.anime.infra.adapter.messenger.dto.PersonSimpleEventDTO;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PersonCreatedPublisher {
    private final StreamBridge streamBridge;

    public PersonCreatedPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @EventListener
    public void publish(PersonCreatedEvent event) {
        PersonSimpleEventDTO dto = new PersonSimpleEventDTO(
                event.getEventId(),
                event.getTimestamp(),
                event.getPersonId()
        );
        streamBridge.send("person-created-out-0", dto);
    }
}
