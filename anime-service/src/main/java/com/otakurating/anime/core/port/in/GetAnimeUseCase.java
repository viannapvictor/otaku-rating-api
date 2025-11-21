package com.otakurating.anime.core.port.in;

import com.otakurating.anime.core.command.GetAnimeCommand;
import com.otakurating.anime.core.model.Anime;

public interface GetAnimeUseCase {
    Anime getById(GetAnimeCommand command);
}
