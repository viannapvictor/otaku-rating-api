package com.otakurating.rating.core.command;

public final class CreateRatingCommand extends BaseCommand {
    private final String ratingId;

    public CreateRatingCommand(String ratingId) {
        this.ratingId = ratingId;
    }

    public String getRatingId() {
        return ratingId;
    }
}
