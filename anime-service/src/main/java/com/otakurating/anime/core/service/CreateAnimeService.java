package com.otakurating.anime.core.service;

import com.otakurating.anime.core.command.CreateAnimeCommand;
import com.otakurating.anime.core.event.AnimeEvent;
import com.otakurating.anime.core.exception.AnimeAlreadyExistsException;
import com.otakurating.anime.core.model.Anime;
import com.otakurating.anime.core.port.in.CreateAnimeUseCase;
import com.otakurating.anime.core.port.out.EventPublisherPort;
import com.otakurating.anime.core.port.out.FindAnimePort;
import com.otakurating.anime.core.port.out.SavePort;
import org.springframework.stereotype.Service;

@Service
public class CreateAnimeService implements CreateAnimeUseCase {
    private final FindAnimePort findAnimePort;
    private final SavePort<Anime> animeSavePort;
    private final EventPublisherPort eventPublisherPort;

    public CreateAnimeService(FindAnimePort findAnimePort, SavePort<Anime> animeSavePort, EventPublisherPort eventPublisherPort) {
        this.findAnimePort = findAnimePort;
        this.animeSavePort = animeSavePort;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    public Anime create(CreateAnimeCommand command) {
        Anime anime = new Anime(command);
        if (findAnimePort.existsById(anime.getId())) {
            throw new AnimeAlreadyExistsException();
        }
        Anime createdAnime = animeSavePort.save(anime);
        for (AnimeEvent event : anime.pullEvents()) {
            eventPublisherPort.publish(event);
        }
        return createdAnime;
    }
}
