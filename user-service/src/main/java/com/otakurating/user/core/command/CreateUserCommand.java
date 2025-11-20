package com.otakurating.user.core.command;

import java.util.List;

public final class CreateUserCommand extends BaseCommand {
    private final String username;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final List<String> roles;
    private final boolean enabled;
    private final boolean emailVerified;

    public CreateUserCommand(String username, String email, String firstName, String lastName,
                            String password, List<String> roles, boolean enabled, boolean emailVerified) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.roles = roles;
        this.enabled = enabled;
        this.emailVerified = emailVerified;
    }

    public String getUsername() {
        return username;
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

    public String getPassword() {
        return password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }
}