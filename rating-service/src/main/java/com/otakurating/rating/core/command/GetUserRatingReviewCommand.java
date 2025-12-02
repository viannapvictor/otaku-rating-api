package com.otakurating.rating.core.command;

public final class GetUserRatingReviewCommand extends BaseCommand {
    private final String ratingId;
    private final String userId;

    public GetUserRatingReviewCommand(String ratingId, String userId) {
        this.ratingId = ratingId;
        this.userId = userId;
    }

    public String getRatingId() {
        return ratingId;
    }

    public String getUserId() {
        return userId;
    }
}
