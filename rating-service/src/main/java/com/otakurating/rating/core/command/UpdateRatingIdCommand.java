package com.otakurating.rating.core.command;

public final class UpdateRatingIdCommand extends BaseCommand {
    private final String currentRatingId;
    private final String newRatingId;

    public UpdateRatingIdCommand(String currentRatingId, String newRatingId) {
        this.currentRatingId = currentRatingId;
        this.newRatingId = newRatingId;
    }

    public String getCurrentRatingId() {
        return currentRatingId;
    }

    public String getNewRatingId() {
        return newRatingId;
    }
}
