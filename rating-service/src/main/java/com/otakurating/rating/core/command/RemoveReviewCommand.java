package com.otakurating.rating.core.command;

public final class RemoveReviewCommand extends BaseCommand {
    private final String currentUserId;
    private final String ratingId;

    public RemoveReviewCommand(String currentUserId, String ratingId) {
        this.currentUserId = currentUserId;
        this.ratingId = ratingId;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public String getRatingId() {
        return ratingId;
    }
}
