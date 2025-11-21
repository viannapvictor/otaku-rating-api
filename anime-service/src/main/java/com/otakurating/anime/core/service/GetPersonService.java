package com.otakurating.anime.core.service;

import com.otakurating.anime.core.command.GetPersonCommand;
import com.otakurating.anime.core.exception.PersonNotFoundException;
import com.otakurating.anime.core.model.Person;
import com.otakurating.anime.core.port.in.GetPersonUseCase;
import com.otakurating.anime.core.port.out.FindPersonPort;
import org.springframework.stereotype.Service;

@Service
public class GetPersonService implements GetPersonUseCase {
    private final FindPersonPort findPersonPort;

    public GetPersonService(FindPersonPort findPersonPort) {
        this.findPersonPort = findPersonPort;
    }

    @Override
    public Person getById(GetPersonCommand command) {
        return findPersonPort.findById(command.getId())
                .orElseThrow(PersonNotFoundException::new);
    }
}
