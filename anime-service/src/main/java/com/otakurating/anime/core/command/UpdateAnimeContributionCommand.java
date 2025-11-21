package com.otakurating.anime.core.command;

import com.otakurating.anime.core.model.CreditRole;

import java.util.UUID;

public final class UpdateAnimeContributionCommand extends BaseCommand {
    private final String animeId;
    private final UUID personId;
    private final CreditRole creditRole;

    public UpdateAnimeContributionCommand(String animeId, UUID personId, CreditRole creditRole) {
        this.animeId = animeId;
        this.personId = personId;
        this.creditRole = creditRole;
    }

    public String getAnimeId() {
        return animeId;
    }

    public UUID getPersonId() {
        return personId;
    }

    public CreditRole getCreditRole() {
        return creditRole;
    }
}
