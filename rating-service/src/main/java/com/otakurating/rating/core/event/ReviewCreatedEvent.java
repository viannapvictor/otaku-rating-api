package com.otakurating.rating.core.event;

public final class ReviewCreatedEvent extends ReviewEvent {
    public ReviewCreatedEvent(String ratingId, String userId, int rate, String comment) {
        super(ratingId, userId, rate, comment);
    }
}
