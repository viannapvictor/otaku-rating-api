package com.otakurating.rating.core.command;

public class GetUserReviewsCommand extends BaseCommand {
    private final String userId;

    public GetUserReviewsCommand(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
