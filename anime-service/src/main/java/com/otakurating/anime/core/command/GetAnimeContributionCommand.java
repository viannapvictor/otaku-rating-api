package com.otakurating.anime.core.command;

import java.util.UUID;

public final class GetAnimeContributionCommand extends BaseCommand {
    private final String animeId;
    private final UUID personId;

    public GetAnimeContributionCommand(String animeId, UUID personId) {
        this.animeId = animeId;
        this.personId = personId;
    }

    public String getAnimeId() {
        return animeId;
    }

    public UUID getPersonId() {
        return personId;
    }
}
