package com.otakurating.anime.core.service;

import com.otakurating.anime.core.command.DeleteAnimeContributionCommand;
import com.otakurating.anime.core.event.AnimeContributionEvent;
import com.otakurating.anime.core.exception.AnimeContributionNotFoundException;
import com.otakurating.anime.core.model.AnimeContribution;
import com.otakurating.anime.core.port.in.DeleteAnimeContributionUseCase;
import com.otakurating.anime.core.port.out.DeleteAnimeContributionPort;
import com.otakurating.anime.core.port.out.EventPublisherPort;
import com.otakurating.anime.core.port.out.FindAnimeContributionPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteAnimeContributionService implements DeleteAnimeContributionUseCase {
    private final FindAnimeContributionPort findAnimeContributionPort;
    private final DeleteAnimeContributionPort deleteAnimeContributionPort;
    private final EventPublisherPort eventPublisherPort;

    public DeleteAnimeContributionService(FindAnimeContributionPort findAnimeContributionPort, DeleteAnimeContributionPort deleteAnimeContributionPort, EventPublisherPort eventPublisherPort) {
        this.findAnimeContributionPort = findAnimeContributionPort;
        this.deleteAnimeContributionPort = deleteAnimeContributionPort;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    public void delete(DeleteAnimeContributionCommand command) {
        AnimeContribution animeContribution = findAnimeContributionPort.findByAnimeIdAndPersonId(
                command.getAnimeId(),
                command.getPersonId()
        ).orElseThrow(AnimeContributionNotFoundException::new);
        animeContribution.delete();
        deleteAnimeContributionPort.deleteByAnimeIdAndPersonId(command.getAnimeId(), command.getPersonId());
        for (AnimeContributionEvent event : animeContribution.pullEvents()) {
            eventPublisherPort.publish(event);
        }
    }
}
