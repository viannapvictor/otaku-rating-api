package com.otakurating.anime.core.port.in;

import com.otakurating.anime.core.command.GetAnimePageCommand;
import com.otakurating.anime.core.model.Anime;
import org.springframework.data.domain.Page;

public interface GetAnimePageUseCase {
    Page<Anime> getPage(GetAnimePageCommand command);
}
