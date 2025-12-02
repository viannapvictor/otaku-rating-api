package com.otakurating.anime.core.service;

import com.otakurating.anime.core.command.CreateAnimeContributionCommand;
import com.otakurating.anime.core.event.AnimeContributionEvent;
import com.otakurating.anime.core.exception.AnimeContributionAlreadyExists;
import com.otakurating.anime.core.model.AnimeContribution;
import com.otakurating.anime.core.port.in.CreateAnimeContributionUseCase;
import com.otakurating.anime.core.port.out.EventPublisherPort;
import com.otakurating.anime.core.port.out.FindAnimeContributionPort;
import com.otakurating.anime.core.port.out.SavePort;
import org.springframework.stereotype.Service;

@Service
public class CreateAnimeContributionService implements CreateAnimeContributionUseCase {
    private final FindAnimeContributionPort findAnimeContributionPort;
    private final SavePort<AnimeContribution> animeContributionSavePort;
    private final EventPublisherPort eventPublisherPort;

    public CreateAnimeContributionService(FindAnimeContributionPort findAnimeContributionPort, SavePort<AnimeContribution> animeContributionSavePort, EventPublisherPort eventPublisherPort) {
        this.findAnimeContributionPort = findAnimeContributionPort;
        this.animeContributionSavePort = animeContributionSavePort;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    public AnimeContribution create(CreateAnimeContributionCommand command) {
        if (findAnimeContributionPort.existsByAnimeIdAndPersonId(command.getAnimeId(), command.getPersonId())) {
            throw new AnimeContributionAlreadyExists();
        }
        AnimeContribution animeContribution = new AnimeContribution(command);
        AnimeContribution createdAnimeContribution = animeContributionSavePort.save(animeContribution);
        for (AnimeContributionEvent event : animeContribution.pullEvents()) {
            eventPublisherPort.publish(event);
        }
        return createdAnimeContribution;
    }
}
