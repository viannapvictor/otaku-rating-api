package com.otaku.rating.core.anime.service;

import com.otaku.rating.core.anime.exception.AnimeContributionAlreadyExists;
import com.otaku.rating.core.anime.exception.AnimeContributionNotFoundException;
import com.otaku.rating.core.anime.model.Anime;
import com.otaku.rating.core.anime.model.AnimeContribution;
import com.otaku.rating.core.anime.repository.AnimeContributionRepository;
import com.otaku.rating.core.person.model.Person;
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
        // Authorization is now handled by @RolesAllowed at controller/facade level
        if (animeContributionRepository.exists(animeContribution.getAnimeId(), animeContribution.getPersonId())) {
            throw new AnimeContributionAlreadyExists();
        }
        return animeContributionRepository.save(animeContribution);
    }

    @Override
    public AnimeContribution update(AnimeContribution animeContribution) {
        Objects.requireNonNull(animeContribution);
        // Authorization is now handled by @RolesAllowed at controller/facade level
        if (!animeContributionRepository.exists(animeContribution.getAnimeId(), animeContribution.getPersonId())) {
            throw new AnimeContributionNotFoundException();
        }
        return animeContributionRepository.save(animeContribution);
    }

    @Override
    public void delete(AnimeContribution animeContribution) {
        Objects.requireNonNull(animeContribution);
        // Authorization is now handled by @RolesAllowed at controller/facade level
        animeContributionRepository.delete(animeContribution);
    }

    @Override
    public void deleteByAnime(Anime anime) {
        Objects.requireNonNull(anime);
        // Authorization is now handled by @RolesAllowed at controller/facade level
        animeContributionRepository.deleteByAnime(anime);
    }

    @Override
    public void deleteByPerson(Person person) {
        Objects.requireNonNull(person);
        // Authorization is now handled by @RolesAllowed at controller/facade level
        animeContributionRepository.deleteByPerson(person);
    }

    @Override
    public AnimeContribution getById(String animeId, UUID personId) {
        return animeContributionRepository.findById(animeId, personId)
                .orElseThrow(AnimeContributionNotFoundException::new);
    }
}
