package com.otakurating.anime.core.port.in;

import com.otakurating.anime.core.command.DeletePersonCommand;

public interface DeletePersonUseCase {
    void delete(DeletePersonCommand command);
}
