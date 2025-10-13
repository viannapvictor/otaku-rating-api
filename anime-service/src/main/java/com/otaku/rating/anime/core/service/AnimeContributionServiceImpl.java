package com.otaku.rating.anime.core.service;

import com.otaku.rating.anime.core.exception.AnimeContributionAlreadyExists;
import com.otaku.rating.anime.core.exception.AnimeContributionNotFoundException;
import com.otaku.rating.anime.core.model.Anime;
import com.otaku.rating.anime.core.model.AnimeContribution;
import com.otaku.rating.anime.core.model.Person;
import com.otaku.rating.anime.core.repository.AnimeContributionRepository;
import com.otaku.rating.generic.core.exception.ForbiddenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnimeContributionServiceImpl implements AnimeContributionService {
    private final AnimeContributionRepository animeContributionRepository;

    @Override
    public AnimeContribution add(User authenticatedUser, AnimeContribution animeContribution) {
        Objects.requireNonNull(authenticatedUser);
        Objects.requireNonNull(animeContribution);
        if (!authenticatedUser.hasAuthorization(UserAuthorizationLevel.MODIFICATION)) {
            throw new ForbiddenException();
        }
        if (animeContributionRepository.exists(animeContribution.getAnimeId(), animeContribution.getPersonId())) {
            throw new AnimeContributionAlreadyExists();
        }
        return animeContributionRepository.save(animeContribution);
    }

    @Override
    public AnimeContribution update(User authenticatedUser, AnimeContribution animeContribution) {
        Objects.requireNonNull(authenticatedUser);
        Objects.requireNonNull(animeContribution);
        if (!authenticatedUser.hasAuthorization(UserAuthorizationLevel.MODIFICATION)) {
            throw new ForbiddenException();
        }
        if (!animeContributionRepository.exists(animeContribution.getAnimeId(), animeContribution.getPersonId())) {
            throw new AnimeContributionNotFoundException();
        }
        return animeContributionRepository.save(animeContribution);
    }

    @Override
    public void delete(User authenticatedUser, AnimeContribution animeContribution) {
        Objects.requireNonNull(authenticatedUser);
        Objects.requireNonNull(animeContribution);
        if (!authenticatedUser.hasAuthorization(UserAuthorizationLevel.MODIFICATION)) {
            throw new ForbiddenException();
        }
        animeContributionRepository.delete(animeContribution);
    }

    @Override
    public void deleteByAnime(User authenticatedUser, Anime anime) {
        Objects.requireNonNull(authenticatedUser);
        Objects.requireNonNull(anime);
        if (!authenticatedUser.hasAuthorization(UserAuthorizationLevel.MODIFICATION)) {
            throw new ForbiddenException();
        }
        animeContributionRepository.deleteByAnime(anime);
    }

    @Override
    public void deleteByPerson(User authenticatedUser, Person person) {
        Objects.requireNonNull(authenticatedUser);
        Objects.requireNonNull(person);
        if (!authenticatedUser.hasAuthorization(UserAuthorizationLevel.MODIFICATION)) {
            throw new ForbiddenException();
        }
        animeContributionRepository.deleteByPerson(person);
    }

    @Override
    public AnimeContribution getById(String animeId, UUID personId) {
        return animeContributionRepository.findById(animeId, personId)
                .orElseThrow(AnimeContributionNotFoundException::new);
    }
}
