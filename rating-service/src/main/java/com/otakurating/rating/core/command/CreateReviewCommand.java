package com.otakurating.rating.core.command;

public final class CreateReviewCommand extends BaseCommand {
    private final String ratingId;
    private final String userId;
    private final Integer rate;
    private final String comment;

    public CreateReviewCommand(String ratingId, String userId, Integer rate, String comment) {
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

    public Integer getRate() {
        return rate;
    }

    public String getComment() {
        return comment;
    }
}
