package com.otakurating.anime.core.service;

import com.otakurating.anime.core.command.UpdateAnimeContributionCommand;
import com.otakurating.anime.core.event.AnimeContributionEvent;
import com.otakurating.anime.core.exception.AnimeContributionNotFoundException;
import com.otakurating.anime.core.model.AnimeContribution;
import com.otakurating.anime.core.port.in.UpdateAnimeContributionUseCase;
import com.otakurating.anime.core.port.out.EventPublisherPort;
import com.otakurating.anime.core.port.out.FindAnimeContributionPort;
import com.otakurating.anime.core.port.out.SavePort;
import org.springframework.stereotype.Service;

@Service
public class UpdateAnimeContributionService implements UpdateAnimeContributionUseCase {
    private final FindAnimeContributionPort findAnimeContributionPort;
    private final SavePort<AnimeContribution> animeContributionSavePort;
    private final EventPublisherPort eventPublisherPort;

    public UpdateAnimeContributionService(FindAnimeContributionPort findAnimeContributionPort, SavePort<AnimeContribution> animeContributionSavePort, EventPublisherPort eventPublisherPort) {
        this.findAnimeContributionPort = findAnimeContributionPort;
        this.animeContributionSavePort = animeContributionSavePort;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    public AnimeContribution update(UpdateAnimeContributionCommand command) {
        AnimeContribution animeContribution = findAnimeContributionPort.findByAnimeIdAndPersonId(
                command.getAnimeId(),
                command.getPersonId()
        ).orElseThrow(AnimeContributionNotFoundException::new);
        animeContribution.update(command);
        AnimeContribution updatedAnimeContribution = animeContributionSavePort.save(animeContribution);
        for (AnimeContributionEvent event : animeContribution.pullEvents()) {
            eventPublisherPort.publish(event);
        }
        return updatedAnimeContribution;
    }
}
