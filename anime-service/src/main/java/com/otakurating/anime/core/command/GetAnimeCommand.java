package com.otakurating.anime.core.command;

import java.util.UUID;

public final class GetAnimeCommand extends BaseCommand {
    private final UUID id;

    public GetAnimeCommand(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
