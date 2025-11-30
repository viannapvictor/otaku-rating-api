package com.otakurating.anime.core.service;

import com.otakurating.anime.core.command.UpdateAnimeCommand;
import com.otakurating.anime.core.event.AnimeEvent;
import com.otakurating.anime.core.exception.AnimeNotFoundException;
import com.otakurating.anime.core.model.Anime;
import com.otakurating.anime.core.port.in.UpdateAnimeUseCase;
import com.otakurating.anime.core.port.out.*;
import org.springframework.stereotype.Service;

@Service
public class UpdateAnimeService implements UpdateAnimeUseCase {
    private final FindAnimePort findAnimePort;
    private final SavePort<Anime> animeSavePort;
    private final EventPublisherPort eventPublisherPort;

    public UpdateAnimeService(
            FindAnimePort findAnimePort,
            SavePort<Anime> animeSavePort,
            EventPublisherPort eventPublisherPort
    ) {
        this.findAnimePort = findAnimePort;
        this.animeSavePort = animeSavePort;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    public Anime update(UpdateAnimeCommand command) {
        Anime anime = findAnimePort.findById(command.getId())
                .orElseThrow(AnimeNotFoundException::new);
        anime.update(command);
        Anime updatedAnime = animeSavePort.save(anime);
        for (AnimeEvent event : anime.pullEvents()) {
            eventPublisherPort.publish(event);
        }
        return updatedAnime;
    }
}
