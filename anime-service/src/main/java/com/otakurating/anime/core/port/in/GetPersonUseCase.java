package com.otakurating.anime.core.port.in;

import com.otakurating.anime.core.command.GetPersonCommand;
import com.otakurating.anime.core.model.Person;

public interface GetPersonUseCase {
    Person getById(GetPersonCommand command);
}
