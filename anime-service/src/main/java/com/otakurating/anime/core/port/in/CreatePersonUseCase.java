package com.otakurating.anime.core.port.in;

import com.otakurating.anime.core.command.CreatePersonCommand;
import com.otakurating.anime.core.model.Person;

public interface CreatePersonUseCase {
    Person create(CreatePersonCommand command);
}
