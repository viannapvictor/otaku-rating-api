package com.otakurating.rating.core.event;

public final class UserReviewsDeletedEvent extends DomainEvent {
    private final String userId;

    public UserReviewsDeletedEvent(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
