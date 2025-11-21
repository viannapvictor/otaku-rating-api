package com.otakurating.rating.core.command;

public final class RemoveRatingCommand extends BaseCommand {
    private final String ratingId;

    public RemoveRatingCommand(String ratingId) {
        this.ratingId = ratingId;
    }

    public String getRatingId() {
        return ratingId;
    }
}
