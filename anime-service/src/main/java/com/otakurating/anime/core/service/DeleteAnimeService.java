package com.otakurating.anime.core.service;

import com.otakurating.anime.core.command.DeleteAnimeCommand;
import com.otakurating.anime.core.event.AnimeEvent;
import com.otakurating.anime.core.exception.AnimeNotFoundException;
import com.otakurating.anime.core.model.Anime;
import com.otakurating.anime.core.port.in.DeleteAnimeUseCase;
import com.otakurating.anime.core.port.out.DeleteAnimeContributionPort;
import com.otakurating.anime.core.port.out.DeleteAnimePort;
import com.otakurating.anime.core.port.out.EventPublisherPort;
import com.otakurating.anime.core.port.out.FindAnimePort;
import org.springframework.stereotype.Service;

@Service
public class DeleteAnimeService implements DeleteAnimeUseCase {
    private final FindAnimePort findAnimePort;
    private final DeleteAnimePort deleteAnimePort;
    private final DeleteAnimeContributionPort deleteAnimeContributionPort;
    private final EventPublisherPort eventPublisherPort;

    public DeleteAnimeService(
            FindAnimePort findAnimePort,
            DeleteAnimePort deleteAnimePort,
            DeleteAnimeContributionPort deleteAnimeContributionPort,
            EventPublisherPort eventPublisherPort
    ) {
        this.findAnimePort = findAnimePort;
        this.deleteAnimePort = deleteAnimePort;
        this.deleteAnimeContributionPort = deleteAnimeContributionPort;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    public void delete(DeleteAnimeCommand command) {
        Anime anime = findAnimePort.findById(command.getId())
                .orElseThrow(AnimeNotFoundException::new);
        anime.delete();
        deleteAnimeContributionPort.deleteByAnimeId(command.getId());
        deleteAnimePort.deleteById(command.getId());
        for (AnimeEvent event : anime.pullEvents()) {
            eventPublisherPort.publish(event);
        }
    }
}
