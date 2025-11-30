package com.otakurating.anime.core.event;

import java.time.LocalDate;
import java.util.UUID;

public final class AnimeDeletedEvent extends AnimeEvent {
    public AnimeDeletedEvent(UUID animeId, String title, String description, LocalDate release) {
        super(animeId, title, description, release);
    }
}
