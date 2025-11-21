package com.otakurating.anime.core.port.in;

import com.otakurating.anime.core.command.GetAnimeContributionCommand;
import com.otakurating.anime.core.model.AnimeContribution;

public interface GetAnimeContributionUseCase {
    AnimeContribution get(GetAnimeContributionCommand command);
}
