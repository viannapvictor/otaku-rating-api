package com.otaku.rating.core.anime.service;

import com.otaku.rating.core.anime.exception.AnimeContributionAlreadyExists;
import com.otaku.rating.core.anime.exception.AnimeContributionNotFoundException;
import com.otaku.rating.core.anime.model.AnimeContribution;
import com.otaku.rating.core.anime.repository.AnimeContributionRepository;
import com.otaku.rating.core.generic.exception.ForbiddenException;
import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.valueobject.UserAuthorizationLevel;
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
    public AnimeContribution getById(String animeId, UUID personId) {
        return animeContributionRepository.findById(animeId, personId)
                .orElseThrow(AnimeContributionNotFoundException::new);
    }
}
