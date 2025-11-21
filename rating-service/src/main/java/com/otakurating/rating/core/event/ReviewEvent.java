package com.otakurating.rating.core.event;

public abstract class ReviewEvent extends DomainEvent {
    private final String ratingId;
    private final String userId;
    private final int rate;
    private final String comment;

    public ReviewEvent(String ratingId, String userId, int rate, String comment) {
        this.ratingId = ratingId;
        this.userId = userId;
        this.rate = rate;
        this.comment = comment;
    }

    public String getRatingId() {
        return ratingId;
    }

    public String getUserId() {
        return userId;
    }

    public int getRate() {
        return rate;
    }

    public String getComment() {
        return comment;
    }
}
