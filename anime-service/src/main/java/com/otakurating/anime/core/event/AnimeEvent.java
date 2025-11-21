package com.otakurating.anime.core.event;

import java.time.LocalDate;

public abstract class AnimeEvent extends DomainEvent {
    private final String animeId;
    private final String title;
    private final String description;
    private final LocalDate release;

    protected AnimeEvent(String animeId, String title, String description, LocalDate release) {
        this.animeId = animeId;
        this.title = title;
        this.description = description;
        this.release = release;
    }

    public String getAnimeId() {
        return animeId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getRelease() {
        return release;
    }
}
