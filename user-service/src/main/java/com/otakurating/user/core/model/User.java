package com.otakurating.user.core.model;

import com.otakurating.user.core.command.CreateUserCommand;
import com.otakurating.user.core.command.UpdateUserCommand;
import com.otakurating.user.core.event.UserDeletedEvent;

import java.util.ArrayList;
import java.util.List;

public final class User {
    private final String id;
    private final String username;
    private String email;
    private String firstName;
    private String lastName;
    private final List<UserDeletedEvent> events;
    private boolean onDelete;

    public User(String id, String username, String email, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.events = new ArrayList<>();
        this.onDelete = false;
    }

    public User(CreateUserCommand command, String generatedId) {
        this.id = generatedId;
        this.username = command.getUsername();
        this.email = command.getEmail();
        this.firstName = command.getFirstName();
        this.lastName = command.getLastName();
        this.events = new ArrayList<>();
        this.onDelete = false;
    }

    public void update(UpdateUserCommand command) {
        if (command.getEmail() != null) {
            this.email = command.getEmail();
        }
        if (command.getFirstName() != null) {
            this.firstName = command.getFirstName();
        }
        if (command.getLastName() != null) {
            this.lastName = command.getLastName();
        }
    }

    public void delete() {
        this.onDelete = true;
        UserDeletedEvent event = new UserDeletedEvent(this.id, this.username);
        this.events.add(event);
    }

    public List<UserDeletedEvent> pullEvents() {
        List<UserDeletedEvent> pulledEvents = List.copyOf(this.events);
        this.events.clear();
        return pulledEvents;
    }

    public String getId() {
        return id;
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

    public boolean isOnDelete() {
        return onDelete;
    }
}