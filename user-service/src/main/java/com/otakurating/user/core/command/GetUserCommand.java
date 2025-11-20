package com.otakurating.user.core.command;

public final class GetUserCommand extends BaseCommand {
    private final String userId;

    public GetUserCommand(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}