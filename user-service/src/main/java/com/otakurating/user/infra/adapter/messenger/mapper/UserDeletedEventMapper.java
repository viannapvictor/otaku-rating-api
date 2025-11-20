package com.otakurating.user.infra.adapter.messenger.mapper;

import com.otakurating.user.core.event.UserDeletedEvent;
import com.otakurating.user.infra.adapter.messenger.dto.UserDeletedEventDTO;
import org.springframework.stereotype.Component;

@Component
public class UserDeletedEventMapper {

    public UserDeletedEventDTO toDTO(UserDeletedEvent event) {
        return new UserDeletedEventDTO(
            event.getEventId(),
            event.getTimestamp(),
            event.getUserId(),
            event.getUsername()
        );
    }

    public UserDeletedEvent fromDTO(UserDeletedEventDTO dto) {
        return new UserDeletedEvent(
            dto.getUserId(),
            dto.getUsername()
        );
    }
}