package com.otaku.rating.core.anime.facade;

import com.otaku.rating.core.anime.model.AnimeContribution;
import com.otaku.rating.core.anime.service.AnimeContributionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AnimeContributionFacadeImpl implements AnimeContributionFacade {
    private final AnimeContributionService animeContributionService;

    @Override
    public AnimeContribution add(AnimeContribution animeContribution) {
        return animeContributionService.add(animeContribution);
    }

    @Override
    public AnimeContribution update(AnimeContribution animeContribution) {
        return animeContributionService.update(animeContribution);
    }

    @Override
    public void delete(AnimeContribution animeContribution) {
        animeContributionService.delete(animeContribution);
    }

    @Override
    public AnimeContribution getById(String animeId, UUID personId) {
        return animeContributionService.getById(animeId, personId);
    }
}
