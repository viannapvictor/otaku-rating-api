package com.otakurating.anime.core.service;

import com.otakurating.anime.core.command.DeletePersonCommand;
import com.otakurating.anime.core.event.PersonEvent;
import com.otakurating.anime.core.exception.PersonNotFoundException;
import com.otakurating.anime.core.model.Person;
import com.otakurating.anime.core.port.in.DeletePersonUseCase;
import com.otakurating.anime.core.port.out.*;
import org.springframework.stereotype.Service;

@Service
public class DeletePersonService implements DeletePersonUseCase {
    private final FindPersonPort findPersonPort;
    private final DeletePersonPort deletePersonPort;
    private final DeleteAnimeContributionPort deleteAnimeContributionPort;
    private final EventPublisherPort eventPublisherPort;

    public DeletePersonService(
            FindPersonPort findPersonPort,
            DeletePersonPort deletePersonPort,
            DeleteAnimeContributionPort deleteAnimeContributionPort,
            EventPublisherPort eventPublisherPort
    ) {
        this.findPersonPort = findPersonPort;
        this.deletePersonPort = deletePersonPort;
        this.deleteAnimeContributionPort = deleteAnimeContributionPort;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    public void delete(DeletePersonCommand command) {
        Person person = findPersonPort.findById(command.getId())
                .orElseThrow(PersonNotFoundException::new);
        person.delete();
        deleteAnimeContributionPort.deleteByPersonId(command.getId());
        deletePersonPort.deleteById(command.getId());
        for (PersonEvent event : person.pullEvents()) {
            eventPublisherPort.publish(event);
        }
    }
}
