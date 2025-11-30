package com.otakurating.anime.core.command;

import java.util.UUID;

public final class DeleteAnimeCommand extends BaseCommand {
    private final UUID id;

    public DeleteAnimeCommand(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
