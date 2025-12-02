package com.otakurating.rating.core.event;

public final class ReviewDeletedEvent extends ReviewEvent {
    public ReviewDeletedEvent(String ratingId, String userId, int rate, String comment) {
        super(ratingId, userId, rate, comment);
    }
}
