package com.otakurating.anime.core.event;

import java.util.UUID;

public abstract class PersonEvent extends DomainEvent {
    private final UUID personId;
    private final String name;
    private final String description;

    protected PersonEvent(UUID personId, String name, String description) {
        this.personId = personId;
        this.name = name;
        this.description = description;
    }

    public UUID getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
