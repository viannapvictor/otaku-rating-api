package com.otakurating.anime.core.event;

import com.otakurating.anime.core.model.CreditRole;

import java.util.UUID;

public final class AnimeContributionCreatedEvent extends AnimeContributionEvent {
    public AnimeContributionCreatedEvent(UUID animeId, UUID personId, CreditRole creditRole) {
        super(animeId, personId, creditRole);
    }
}
