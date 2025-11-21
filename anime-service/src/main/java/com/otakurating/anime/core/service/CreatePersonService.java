package com.otakurating.anime.core.service;

import com.otakurating.anime.core.command.CreatePersonCommand;
import com.otakurating.anime.core.event.PersonEvent;
import com.otakurating.anime.core.model.Person;
import com.otakurating.anime.core.port.in.CreatePersonUseCase;
import com.otakurating.anime.core.port.out.EventPublisherPort;
import com.otakurating.anime.core.port.out.SavePort;
import org.springframework.stereotype.Service;

@Service
public class CreatePersonService implements CreatePersonUseCase {
    private final SavePort<Person> personSavePort;
    private final EventPublisherPort eventPublisherPort;

    public CreatePersonService(SavePort<Person> personSavePort, EventPublisherPort eventPublisherPort) {
        this.personSavePort = personSavePort;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    public Person create(CreatePersonCommand command) {
        Person person = new Person(command);
        Person createdPerson = personSavePort.save(person);
        for (PersonEvent event : person.pullEvents()) {
            eventPublisherPort.publish(event);
        }
        return createdPerson;
    }
}
