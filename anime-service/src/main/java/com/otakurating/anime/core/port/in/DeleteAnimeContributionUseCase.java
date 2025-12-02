package com.otakurating.anime.core.port.in;

import com.otakurating.anime.core.command.DeleteAnimeContributionCommand;

public interface DeleteAnimeContributionUseCase {
    void delete(DeleteAnimeContributionCommand command);
}
