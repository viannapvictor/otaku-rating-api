package com.otakurating.anime.core.service;

import com.otakurating.anime.core.exception.AnimeContributionAlreadyExists;
import com.otakurating.anime.core.exception.AnimeContributionNotFoundException;
import com.otakurating.anime.core.model.Anime;
import com.otakurating.anime.core.model.AnimeContribution;
import com.otakurating.anime.core.model.Person;
import com.otakurating.anime.core.repository.AnimeContributionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnimeContributionServiceImpl implements AnimeContributionService {
    private final AnimeContributionRepository animeContributionRepository;

    @Override
    public AnimeContribution add(AnimeContribution animeContribution) {
        Objects.requireNonNull(animeContribution);
        if (animeContributionRepository.exists(animeContribution.getAnimeId(), animeContribution.getPersonId())) {
            throw new AnimeContributionAlreadyExists();
        }
        return animeContributionRepository.save(animeContribution);
    }

    @Override
    public AnimeContribution update(AnimeContribution animeContribution) {
        Objects.requireNonNull(animeContribution);
        if (!animeContributionRepository.exists(animeContribution.getAnimeId(), animeContribution.getPersonId())) {
            throw new AnimeContributionNotFoundException();
        }
        return animeContributionRepository.save(animeContribution);
    }

    @Override
    public void delete(AnimeContribution animeContribution) {
        Objects.requireNonNull(animeContribution);
        animeContributionRepository.delete(animeContribution);
    }

    @Override
    public void deleteByAnime(Anime anime) {
        Objects.requireNonNull(anime);
        animeContributionRepository.deleteByAnime(anime);
    }

    @Override
    public void deleteByPerson(Person person) {
        Objects.requireNonNull(person);
        animeContributionRepository.deleteByPerson(person);
    }

    @Override
    public AnimeContribution getById(String animeId, UUID personId) {
        return animeContributionRepository.findById(animeId, personId)
                .orElseThrow(AnimeContributionNotFoundException::new);
    }
}
