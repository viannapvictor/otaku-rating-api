package com.otakurating.anime.core.model;

import com.otakurating.anime.core.command.CreatePersonCommand;
import com.otakurating.anime.core.command.UpdatePersonCommand;
import com.otakurating.anime.core.event.PersonCreatedEvent;
import com.otakurating.anime.core.event.PersonDeletedEvent;
import com.otakurating.anime.core.event.PersonEvent;
import com.otakurating.anime.core.event.PersonUpdatedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class Person {
    private final UUID id;
    private final List<PersonEvent> events;

    private PersonName name;
    private PersonDescription description;

    public Person(UUID id, PersonName name, PersonDescription description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.events = new ArrayList<>();
    }

    public Person(CreatePersonCommand command) {
        this.id = UUID.randomUUID();
        this.name = PersonName.valueOf(command.getName());
        this.description = PersonDescription.valueOf(command.getDescription());
        this.events = new ArrayList<>();

        PersonEvent event = new PersonCreatedEvent(
                this.id,
                this.name.getValue(),
                this.description.getValue()
        );
        this.events.add(event);
    }

    public void update(UpdatePersonCommand command) {
        this.name = PersonName.valueOf(command.getName());
        this.description = PersonDescription.valueOf(command.getDescription());

        PersonEvent event = new PersonUpdatedEvent(
                this.id,
                this.name.getValue(),
                this.description.getValue()
        );
        this.events.add(event);
    }

    public void delete() {
        PersonEvent event = new PersonDeletedEvent(
                this.id,
                this.name.getValue(),
                this.description.getValue()
        );
        this.events.add(event);
    }

    public List<PersonEvent> pullEvents() {
        List<PersonEvent> pulledEvents = List.copyOf(this.events);
        this.events.clear();

        return pulledEvents;
    }

    public UUID getId() {
        return id;
    }

    public PersonName getName() {
        return name;
    }

    public PersonDescription getDescription() {
        return description;
    }
}
