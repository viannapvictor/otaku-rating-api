package com.otakurating.anime.core.facade;

import com.otakurating.anime.core.model.AnimeContribution;
import com.otakurating.anime.core.service.AnimeContributionService;
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
