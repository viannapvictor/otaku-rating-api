package com.otakurating.user.core.command;

public final class DeleteUserCommand extends BaseCommand {
    private final String userId;

    public DeleteUserCommand(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}