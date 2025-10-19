package com.otaku.rating.core.anime.service;

import com.otaku.rating.core.anime.model.Anime;
import com.otaku.rating.core.anime.model.AnimeContribution;
import com.otaku.rating.core.person.model.Person;

import java.util.UUID;

public interface AnimeContributionService {
    AnimeContribution add(AnimeContribution animeContribution);
    AnimeContribution update(AnimeContribution animeContribution);
    void delete(AnimeContribution animeContribution);
    void deleteByAnime(Anime anime);
    void deleteByPerson(Person person);
    AnimeContribution getById(String animeId, UUID personId);
}
