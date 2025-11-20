package com.otakurating.anime.core.event;

import java.time.LocalDate;

public final class AnimeCreatedEvent extends AnimeEvent {
    public AnimeCreatedEvent(String animeId, String title, String description, LocalDate release) {
        super(animeId, title, description, release);
    }
}
