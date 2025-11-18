package com.otakurating.rating.core.event;

public final class RatingDeletedEvent extends RatingEvent {
    public RatingDeletedEvent(String ratingId, long totalRate, int reviewsCount) {
        super(ratingId, totalRate, reviewsCount);
    }
}
