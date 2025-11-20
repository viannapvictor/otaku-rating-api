package com.otakurating.user.core.event;

import java.util.UUID;

public final class UserDeletedEvent extends BaseEvent<UUID> {
    private final String userId;
    private final String username;

    public UserDeletedEvent(String userId, String username) {
        super(UUID.randomUUID());
        this.userId = userId;
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }
}