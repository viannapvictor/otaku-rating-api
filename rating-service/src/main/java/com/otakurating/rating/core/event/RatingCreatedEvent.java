package com.otakurating.rating.core.event;

public final class RatingCreatedEvent extends RatingEvent {
    public RatingCreatedEvent(String ratingId, long totalRate, int reviewsCount) {
        super(ratingId, totalRate, reviewsCount);
    }
}
