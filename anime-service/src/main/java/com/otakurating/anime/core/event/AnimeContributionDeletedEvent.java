package com.otakurating.anime.core.event;

import com.otakurating.anime.core.model.CreditRole;

import java.util.UUID;

public final class AnimeContributionDeletedEvent extends AnimeContributionEvent {
    public AnimeContributionDeletedEvent(String animeId, UUID personId, CreditRole creditRole) {
        super(animeId, personId, creditRole);
    }
}
