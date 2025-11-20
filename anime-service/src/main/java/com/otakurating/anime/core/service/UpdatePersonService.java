package com.otakurating.anime.core.service;

import com.otakurating.anime.core.command.UpdatePersonCommand;
import com.otakurating.anime.core.event.PersonEvent;
import com.otakurating.anime.core.exception.PersonNotFoundException;
import com.otakurating.anime.core.model.Person;
import com.otakurating.anime.core.port.in.UpdatePersonUseCase;
import com.otakurating.anime.core.port.out.EventPublisherPort;
import com.otakurating.anime.core.port.out.FindPersonPort;
import com.otakurating.anime.core.port.out.SavePort;
import org.springframework.stereotype.Service;

@Service
public class UpdatePersonService implements UpdatePersonUseCase {
    private final FindPersonPort findPersonPort;
    private final SavePort<Person> personSavePort;
    private final EventPublisherPort eventPublisherPort;

    public UpdatePersonService(FindPersonPort findPersonPort, SavePort<Person> personSavePort, EventPublisherPort eventPublisherPort) {
        this.findPersonPort = findPersonPort;
        this.personSavePort = personSavePort;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    public Person update(UpdatePersonCommand command) {
        Person person = findPersonPort.findById(command.getCurrentId())
                .orElseThrow(PersonNotFoundException::new);
        person.update(command);
        Person updatedPerson = personSavePort.save(person);
        for (PersonEvent event : person.pullEvents()) {
            eventPublisherPort.publish(event);
        }
        return updatedPerson;
    }
}
