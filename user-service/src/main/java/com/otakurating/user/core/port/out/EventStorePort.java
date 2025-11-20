package com.otakurating.user.core.port.out;

import com.otakurating.user.core.event.BaseEvent;

import java.util.UUID;

public interface EventStorePort {
    void store(BaseEvent<UUID> event, String aggregateType, String aggregateId);
}