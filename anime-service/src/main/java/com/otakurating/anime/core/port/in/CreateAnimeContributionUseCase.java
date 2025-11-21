package com.otakurating.anime.core.port.in;

import com.otakurating.anime.core.command.CreateAnimeContributionCommand;
import com.otakurating.anime.core.model.AnimeContribution;

public interface CreateAnimeContributionUseCase {
    AnimeContribution create(CreateAnimeContributionCommand command);
}
