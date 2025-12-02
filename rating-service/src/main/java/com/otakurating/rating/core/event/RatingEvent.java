package com.otakurating.rating.core.event;

public abstract class RatingEvent extends DomainEvent {
    private final String ratingId;
    private final long totalRate;
    private final int reviewsCount;

    public RatingEvent(String ratingId, long totalRate, int reviewsCount) {
        this.ratingId = ratingId;
        this.totalRate = totalRate;
        this.reviewsCount = reviewsCount;
    }

    public String getRatingId() {
        return ratingId;
    }

    public long getTotalRate() {
        return totalRate;
    }

    public int getReviewsCount() {
        return reviewsCount;
    }
}
