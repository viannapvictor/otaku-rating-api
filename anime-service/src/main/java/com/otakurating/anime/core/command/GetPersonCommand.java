package com.otakurating.anime.core.command;

import java.util.UUID;

public final class GetPersonCommand extends BaseCommand {
    private final UUID id;

    public GetPersonCommand(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
