package com.otakurating.user.infra.adapter.persistence.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.otakurating.user.core.event.BaseEvent;
import com.otakurating.user.core.port.out.EventStorePort;
import com.otakurating.user.infra.adapter.persistence.entity.StoredEvent;
import com.otakurating.user.infra.adapter.persistence.repository.EventStoreRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class EventStoreAdapter implements EventStorePort {

    private final EventStoreRepository repository;
    private final ObjectMapper objectMapper;

    public EventStoreAdapter(EventStoreRepository repository) {
        this.repository = repository;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    @Transactional
    public void store(BaseEvent<UUID> event, String aggregateType, String aggregateId) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            String eventType = event.getClass().getSimpleName();

            StoredEvent storedEvent = new StoredEvent(
                event.getEventId(),
                aggregateType,
                aggregateId,
                eventType,
                payload,
                event.getTimestamp()
            );

            repository.save(storedEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize event to JSON", e);
        }
    }
}