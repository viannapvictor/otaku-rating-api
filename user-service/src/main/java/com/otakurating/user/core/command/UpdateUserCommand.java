package com.otakurating.user.core.command;

public final class UpdateUserCommand extends BaseCommand {
    private final String userId;
    private final String email;
    private final String firstName;
    private final String lastName;

    public UpdateUserCommand(String userId, String email, String firstName, String lastName) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}