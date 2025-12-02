package com.otakurating.rating.core.event;

public final class RatingUpdatedEvent extends RatingEvent {
    public RatingUpdatedEvent(String ratingId, long totalRate, int reviewsCount) {
        super(ratingId, totalRate, reviewsCount);
    }
}
