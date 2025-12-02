package com.otakurating.anime.core.command;

import java.util.UUID;

public final class GetAnimeContributionCommand extends BaseCommand {
    private final UUID animeId;
    private final UUID personId;

    public GetAnimeContributionCommand(UUID animeId, UUID personId) {
        this.animeId = animeId;
        this.personId = personId;
    }

    public UUID getAnimeId() {
        return animeId;
    }

    public UUID getPersonId() {
        return personId;
    }
}
