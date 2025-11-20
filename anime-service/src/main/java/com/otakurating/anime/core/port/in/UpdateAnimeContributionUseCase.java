package com.otakurating.anime.core.port.in;

import com.otakurating.anime.core.command.UpdateAnimeContributionCommand;
import com.otakurating.anime.core.model.AnimeContribution;

public interface UpdateAnimeContributionUseCase {
    AnimeContribution update(UpdateAnimeContributionCommand command);
}
