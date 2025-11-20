package com.otakurating.anime.core.event;

import java.time.LocalDate;

public final class AnimeDeletedEvent extends AnimeEvent {
    public AnimeDeletedEvent(String animeId, String title, String description, LocalDate release) {
        super(animeId, title, description, release);
    }
}
