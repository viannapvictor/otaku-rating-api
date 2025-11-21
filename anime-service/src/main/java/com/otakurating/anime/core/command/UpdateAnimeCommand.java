package com.otakurating.anime.core.command;

import java.time.LocalDate;

public final class UpdateAnimeCommand extends BaseCommand {
    private final String currentId;
    private final String title;
    private final String description;
    private final LocalDate release;

    public UpdateAnimeCommand(String currentId, String title, String description, LocalDate release) {
        this.currentId = currentId;
        this.title = title;
        this.description = description;
        this.release = release;
    }

    public String getCurrentId() {
        return currentId;
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
