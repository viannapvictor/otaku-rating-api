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

public final class Anime {
    private final List<AnimeEvent> events;

    private String id;
    private AnimeTitle title;
    private AnimeDescription description;
    private AnimeRelease release;

    public Anime(String id, AnimeTitle title, AnimeDescription description, AnimeRelease release) {
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
        this.id = generateIdentifier(this.title);
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

    public String getId() {
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
        String oldId = this.id;
        if (!Objects.equals(command.getCurrentId(), this.id)) {
            throw new IllegalArgumentException("The command must have the current id the same as the anime id.");
        }
        this.title = AnimeTitle.valueOf(command.getTitle());
        this.description = AnimeDescription.valueOf(command.getDescription());
        this.release = AnimeRelease.valueOf(command.getRelease());
        this.id = generateIdentifier(this.title);

        AnimeEvent event = new AnimeUpdatedEvent(
                this.id,
                this.title.getValue(),
                this.description.getValue(),
                this.release.getValue(),
                oldId
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

    private static String generateIdentifier(AnimeTitle title) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < title.getValue().length(); i++) {
            char c = Character.toLowerCase(title.getValue().charAt(i));
            if (c == ' ') {
                sb.append('-');
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
