package com.otakurating.anime.core.command;

import java.time.LocalDate;

public final class CreateAnimeCommand extends BaseCommand {
    private final String title;
    private final String description;
    private final LocalDate release;

    public CreateAnimeCommand(String title, String description, LocalDate release) {
        this.title = title;
        this.description = description;
        this.release = release;
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
