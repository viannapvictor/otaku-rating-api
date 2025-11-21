package com.otakurating.anime.core.service;

import com.otakurating.anime.core.command.GetAnimeContributionCommand;
import com.otakurating.anime.core.exception.AnimeContributionNotFoundException;
import com.otakurating.anime.core.model.AnimeContribution;
import com.otakurating.anime.core.port.in.GetAnimeContributionUseCase;
import com.otakurating.anime.core.port.out.FindAnimeContributionPort;
import org.springframework.stereotype.Service;

@Service
public class GetAnimeContributionService implements GetAnimeContributionUseCase {
    private final FindAnimeContributionPort findAnimeContributionPort;

    public GetAnimeContributionService(FindAnimeContributionPort findAnimeContributionPort) {
        this.findAnimeContributionPort = findAnimeContributionPort;
    }

    @Override
    public AnimeContribution get(GetAnimeContributionCommand command) {
        return findAnimeContributionPort.findByAnimeIdAndPersonId(command.getAnimeId(), command.getPersonId())
                .orElseThrow(AnimeContributionNotFoundException::new);
    }
}
