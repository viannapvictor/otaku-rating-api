package com.otaku.rating.core.anime.factory;

import com.otaku.rating.core.anime.model.AnimeContribution;

public interface AnimeContributionFactory {
    AnimeContribution create(AnimeContributionParams params);
}
