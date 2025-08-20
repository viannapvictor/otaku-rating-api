package com.otaku.rating.core.anime.facade;

import com.otaku.rating.core.anime.model.AnimeContribution;
import com.otaku.rating.core.anime.service.AnimeContributionService;
import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.service.ContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AnimeContributionFacadeImpl implements AnimeContributionFacade {
    private final AnimeContributionService animeContributionService;
    private final ContextService contextService;

    @Override
    public AnimeContribution add(AnimeContribution animeContribution) {
        User user = contextService.getUserOrThrow();
        return animeContributionService.add(user, animeContribution);
    }

    @Override
    public AnimeContribution update(AnimeContribution animeContribution) {
        User user = contextService.getUserOrThrow();
        return animeContributionService.update(user, animeContribution);
    }

    @Override
    public void delete(AnimeContribution animeContribution) {
        User user = contextService.getUserOrThrow();
        animeContributionService.delete(user, animeContribution);
    }

    @Override
    public AnimeContribution getById(String animeId, UUID personId) {
        return animeContributionService.getById(animeId, personId);
    }
}
