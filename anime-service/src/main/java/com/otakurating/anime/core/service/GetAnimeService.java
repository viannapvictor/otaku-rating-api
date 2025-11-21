package com.otakurating.anime.core.service;

import com.otakurating.anime.core.command.GetAnimeCommand;
import com.otakurating.anime.core.exception.AnimeNotFoundException;
import com.otakurating.anime.core.model.Anime;
import com.otakurating.anime.core.port.in.GetAnimeUseCase;
import com.otakurating.anime.core.port.out.FindAnimePort;
import org.springframework.stereotype.Service;

@Service
public class GetAnimeService implements GetAnimeUseCase {
    private final FindAnimePort findAnimePort;

    public GetAnimeService(FindAnimePort findAnimePort) {
        this.findAnimePort = findAnimePort;
    }

    @Override
    public Anime getById(GetAnimeCommand command) {
        return findAnimePort.findById(command.getId())
                .orElseThrow(AnimeNotFoundException::new);
    }
}
