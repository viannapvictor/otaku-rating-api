package com.otakurating.anime.core.command;

import java.time.LocalDate;
import java.util.UUID;

public final class UpdateAnimeCommand extends BaseCommand {
    private final UUID id;
    private final String title;
    private final String description;
    private final LocalDate release;

    public UpdateAnimeCommand(UUID id, String title, String description, LocalDate release) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.release = release;
    }

    public UUID getId() {
        return id;
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
