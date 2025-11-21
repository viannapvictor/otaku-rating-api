package com.otakurating.anime.core.port.in;

import com.otakurating.anime.core.command.UpdateAnimeCommand;
import com.otakurating.anime.core.model.Anime;

public interface UpdateAnimeUseCase {
    Anime update(UpdateAnimeCommand command);
}
