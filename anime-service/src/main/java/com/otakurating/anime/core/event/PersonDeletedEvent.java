package com.otakurating.anime.core.event;

import java.util.UUID;

public final class PersonDeletedEvent extends PersonEvent {
    public PersonDeletedEvent(UUID personId, String name, String description) {
        super(personId, name, description);
    }
}
