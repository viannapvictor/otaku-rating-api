package com.otakurating.anime.core.port.in;

import com.otakurating.anime.core.command.UpdatePersonCommand;
import com.otakurating.anime.core.model.Person;

public interface UpdatePersonUseCase {
    Person update(UpdatePersonCommand command);
}
