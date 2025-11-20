package com.otakurating.user.infra.adapter.messenger.adapter;

import com.otakurating.user.core.event.UserDeletedEvent;
import com.otakurating.user.infra.adapter.messenger.dto.UserDeletedEventDTO;
import com.otakurating.user.infra.adapter.messenger.mapper.UserDeletedEventMapper;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventPublisher {
    private final StreamBridge streamBridge;
    private final UserDeletedEventMapper mapper;

    public UserEventPublisher(StreamBridge streamBridge, UserDeletedEventMapper mapper) {
        this.streamBridge = streamBridge;
        this.mapper = mapper;
    }

    @EventListener
    public void handleUserDeletedEvent(UserDeletedEvent event) {
        UserDeletedEventDTO dto = mapper.toDTO(event);
        streamBridge.send("user-deleted-out-0", dto);
    }
}