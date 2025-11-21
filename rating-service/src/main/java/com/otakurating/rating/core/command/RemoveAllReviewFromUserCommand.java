package com.otakurating.rating.core.command;

public final class RemoveAllReviewFromUserCommand extends BaseCommand {
    private final String userId;

    public RemoveAllReviewFromUserCommand(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
