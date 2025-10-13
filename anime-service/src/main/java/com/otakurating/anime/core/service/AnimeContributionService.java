package com.otakurating.anime.core.service;

import com.otakurating.anime.core.model.Anime;
import com.otakurating.anime.core.model.AnimeContribution;
import com.otakurating.anime.core.model.Person;

import java.util.UUID;

public interface AnimeContributionService {
    AnimeContribution add(AnimeContribution animeContribution);
    AnimeContribution update(AnimeContribution animeContribution);
    void delete(AnimeContribution animeContribution);
    void deleteByAnime(Anime anime);
    void deleteByPerson(Person person);
    AnimeContribution getById(String animeId, UUID personId);
}
