package com.otakurating.anime.core.event;

import com.otakurating.anime.core.model.CreditRole;

import java.util.UUID;

public abstract class AnimeContributionEvent extends DomainEvent {
    private final String animeId;
    private final UUID personId;
    private final CreditRole creditRole;

    protected AnimeContributionEvent(String animeId, UUID personId, CreditRole creditRole) {
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
