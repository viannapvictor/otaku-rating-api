package com.otakurating.anime.core.port.in;

import com.otakurating.anime.core.command.CreateAnimeCommand;
import com.otakurating.anime.core.model.Anime;

public interface CreateAnimeUseCase {
    Anime create(CreateAnimeCommand command);
}
