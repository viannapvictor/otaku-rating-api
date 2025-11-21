package com.otakurating.anime.core.event;

import java.time.LocalDate;

public final class AnimeUpdatedEvent extends AnimeEvent {
    private final String oldAnimeId;

    public AnimeUpdatedEvent(String animeId, String title, String description, LocalDate release, String oldAnimeId) {
        super(animeId, title, description, release);
        this.oldAnimeId = oldAnimeId;
    }

    public String getOldAnimeId() {
        return oldAnimeId;
    }
}
