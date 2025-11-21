package com.otakurating.anime.core.event;

import java.util.UUID;

public final class PersonUpdatedEvent extends PersonEvent {
    public PersonUpdatedEvent(UUID personId, String name, String description) {
        super(personId, name, description);
    }
}
