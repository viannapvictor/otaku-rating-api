package com.otakurating.anime.core.command;

import java.util.UUID;

public final class DeletePersonCommand extends BaseCommand {
    private final UUID id;

    public DeletePersonCommand(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
