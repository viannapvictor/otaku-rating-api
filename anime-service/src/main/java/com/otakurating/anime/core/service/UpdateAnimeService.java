package com.otakurating.anime.core.service;

import com.otakurating.anime.core.command.UpdateAnimeCommand;
import com.otakurating.anime.core.event.AnimeEvent;
import com.otakurating.anime.core.exception.AnimeNotFoundException;
import com.otakurating.anime.core.model.Anime;
import com.otakurating.anime.core.model.AnimeContribution;
import com.otakurating.anime.core.port.in.UpdateAnimeUseCase;
import com.otakurating.anime.core.port.out.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UpdateAnimeService implements UpdateAnimeUseCase {
    private final FindAnimePort findAnimePort;
    private final FindAnimeContributionPort findAnimeContributionPort;
    private final DeleteAnimePort deleteAnimePort;
    private final SavePort<Anime> animeSavePort;
    private final SavePort<AnimeContribution> animeContributionSavePort;
    private final EventPublisherPort eventPublisherPort;

    public UpdateAnimeService(
            FindAnimePort findAnimePort,
            FindAnimeContributionPort findAnimeContributionPort,
            DeleteAnimePort deleteAnimePort,
            SavePort<Anime> animeSavePort,
            SavePort<AnimeContribution> animeContributionSavePort,
            EventPublisherPort eventPublisherPort
    ) {
        this.findAnimePort = findAnimePort;
        this.findAnimeContributionPort = findAnimeContributionPort;
        this.deleteAnimePort = deleteAnimePort;
        this.animeSavePort = animeSavePort;
        this.animeContributionSavePort = animeContributionSavePort;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    public Anime update(UpdateAnimeCommand command) {
        Anime anime = findAnimePort.findById(command.getCurrentId())
                .orElseThrow(AnimeNotFoundException::new);
        anime.update(command);
        if (Objects.equals(anime.getId(), command.getCurrentId())) {
            return persistAnimeAndPublish(anime);
        }
        List<AnimeContribution> contributions = findAnimeContributionPort.findByAnimeId(command.getCurrentId());
        for (AnimeContribution contribution : contributions) {
            contribution.setAnimeId(anime.getId());
            animeContributionSavePort.save(contribution);
        }
        deleteAnimePort.deleteById(command.getCurrentId());
        return persistAnimeAndPublish(anime);
    }

    private Anime persistAnimeAndPublish(Anime anime) {
        Anime updatedAnime = animeSavePort.save(anime);
        for (AnimeEvent event : anime.pullEvents()) {
            eventPublisherPort.publish(event);
        }
        return updatedAnime;
    }
}
