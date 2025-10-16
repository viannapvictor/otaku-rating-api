package com.otaku.rating.anime.core.service;

import com.otaku.rating.anime.core.model.Anime;
import com.otaku.rating.anime.core.model.AnimeContribution;
import com.otaku.rating.anime.core.model.Person;

import java.util.UUID;

public interface AnimeContributionService {
    AnimeContribution add(User authenticatedUser, AnimeContribution animeContribution);
    AnimeContribution update(User authenticatedUser, AnimeContribution animeContribution);
    void delete(User authenticatedUser, AnimeContribution animeContribution);
    void deleteByAnime(User authenticatedUser, Anime anime);
    void deleteByPerson(User authenticatedUser, Person person);
    AnimeContribution getById(String animeId, UUID personId);
}
