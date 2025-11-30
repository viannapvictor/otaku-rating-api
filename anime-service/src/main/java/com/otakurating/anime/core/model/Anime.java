package com.otakurating.anime.core.model;

import com.otakurating.anime.core.command.CreateAnimeCommand;
import com.otakurating.anime.core.command.UpdateAnimeCommand;
import com.otakurating.anime.core.event.AnimeCreatedEvent;
import com.otakurating.anime.core.event.AnimeDeletedEvent;
import com.otakurating.anime.core.event.AnimeEvent;
import com.otakurating.anime.core.event.AnimeUpdatedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class Anime {
    private final List<AnimeEvent> events;
    private final UUID id;

    private AnimeTitle title;
    private AnimeDescription description;
    private AnimeRelease release;

    public Anime(UUID id, AnimeTitle title, AnimeDescription description, AnimeRelease release) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.release = release;
        this.events = new ArrayList<>();
    }

    public Anime(CreateAnimeCommand command) {
        this.title = AnimeTitle.valueOf(command.getTitle());
        this.description = AnimeDescription.valueOf(command.getDescription());
        this.release = AnimeRelease.valueOf(command.getRelease());
        this.id = UUID.randomUUID();
        this.events = new ArrayList<>();

        AnimeEvent event = new AnimeCreatedEvent(
                this.id,
                this.title.getValue(),
                this.description.getValue(),
                this.release.getValue()
        );
        this.events.add(event);
    }

    public List<AnimeEvent> pullEvents() {
        List<AnimeEvent> pulledEvents = List.copyOf(this.events);
        this.events.clear();

        return pulledEvents;
    }

    public UUID getId() {
        return id;
    }

    public AnimeTitle getTitle() {
        return title;
    }

    public AnimeDescription getDescription() {
        return description;
    }

    public AnimeRelease getRelease() {
        return release;
    }

    public void update(UpdateAnimeCommand command) {
        if (!Objects.equals(command.getId(), this.id)) {
            throw new IllegalArgumentException("The command must have the current id the same as the anime id.");
        }
        this.title = AnimeTitle.valueOf(command.getTitle());
        this.description = AnimeDescription.valueOf(command.getDescription());
        this.release = AnimeRelease.valueOf(command.getRelease());

        AnimeEvent event = new AnimeUpdatedEvent(
                this.id,
                this.title.getValue(),
                this.description.getValue(),
                this.release.getValue()
        );
        this.events.add(event);
    }

    public void delete() {
        AnimeEvent event = new AnimeDeletedEvent(
                this.id,
                this.title.getValue(),
                this.description.getValue(),
                this.release.getValue()
        );
        this.events.add(event);
    }
}
