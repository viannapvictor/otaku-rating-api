package com.otakurating.anime.core.port.in;

import com.otakurating.anime.core.command.DeleteAnimeCommand;

public interface DeleteAnimeUseCase {
    void delete(DeleteAnimeCommand command);
}
