package com.otakurating.rating.core.event;

public final class ReviewUpdatedEvent extends ReviewEvent {
    public ReviewUpdatedEvent(String ratingId, String userId, int rate, String comment) {
        super(ratingId, userId, rate, comment);
    }
}
