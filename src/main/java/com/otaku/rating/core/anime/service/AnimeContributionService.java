package com.otaku.rating.core.anime.service;

import com.otaku.rating.core.anime.model.AnimeContribution;
import com.otaku.rating.core.user.model.User;

import java.util.UUID;

public interface AnimeContributionService {
    AnimeContribution add(User authenticatedUser, AnimeContribution animeContribution);
    AnimeContribution update(User authenticatedUser, AnimeContribution animeContribution);
    void delete(User authenticatedUser, AnimeContribution animeContribution);
    AnimeContribution getById(String animeId, UUID personId);
}
