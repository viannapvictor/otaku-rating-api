package com.otakurating.anime.core.model;

import com.otakurating.anime.core.command.CreateAnimeContributionCommand;
import com.otakurating.anime.core.command.UpdateAnimeContributionCommand;
import com.otakurating.anime.core.event.AnimeContributionCreatedEvent;
import com.otakurating.anime.core.event.AnimeContributionDeletedEvent;
import com.otakurating.anime.core.event.AnimeContributionEvent;
import com.otakurating.anime.core.event.AnimeContributionUpdatedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class AnimeContribution {
    private final List<AnimeContributionEvent> events;
    private final UUID personId;
    private final UUID animeId;

    private CreditRole creditRole;

    public AnimeContribution(UUID animeId, UUID personId, CreditRole creditRole) {
        this.animeId = animeId;
        this.personId = personId;
        this.creditRole = creditRole;
        this.events = new ArrayList<>();
    }

    public AnimeContribution(CreateAnimeContributionCommand command) {
        this.animeId = command.getAnimeId();
        this.personId = command.getPersonId();
        this.creditRole = command.getCreditRole();
        this.events = new ArrayList<>();

        AnimeContributionEvent event = new AnimeContributionCreatedEvent(
                this.animeId,
                this.personId,
                this.creditRole
        );
        this.events.add(event);
    }

    public List<AnimeContributionEvent> pullEvents() {
        List<AnimeContributionEvent> pulledEvents = List.copyOf(this.events);
        this.events.clear();

        return pulledEvents;
    }

    public void update(UpdateAnimeContributionCommand command) {
        this.creditRole = command.getCreditRole();
        AnimeContributionEvent event = new AnimeContributionUpdatedEvent(
                this.animeId,
                this.personId,
                this.creditRole
        );
        this.events.add(event);
    }

    public void delete() {
        AnimeContributionEvent event = new AnimeContributionDeletedEvent(
                this.animeId,
                this.personId,
                this.creditRole
        );
        this.events.add(event);
    }

    public UUID getPersonId() {
        return personId;
    }

    public UUID getAnimeId() {
        return animeId;
    }

    public CreditRole getCreditRole() {
        return creditRole;
    }
}
