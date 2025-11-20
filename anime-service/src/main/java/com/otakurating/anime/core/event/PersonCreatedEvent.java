package com.otakurating.anime.core.event;

import java.util.UUID;

public final class PersonCreatedEvent extends PersonEvent {
    public PersonCreatedEvent(UUID personId, String name, String description) {
        super(personId, name, description);
    }
}
