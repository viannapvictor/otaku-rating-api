package com.otakurating.anime.core.facade;

import com.otakurating.anime.core.model.Anime;
import com.otakurating.anime.core.service.AnimeContributionService;
import com.otakurating.anime.core.service.AnimeService;
import com.otakurating.anime.core.strategy.AnimeSearchStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AnimeFacadeImpl implements AnimeFacade {
    private final AnimeService animeService;
    private final AnimeContributionService animeContributionService;

    public Page<Anime> getPage(int page, int size, List<AnimeSearchStrategy> strategies) {
        return animeService.getPage(page, size, strategies);
    }

    public Anime add(Anime anime) {
        return animeService.add(anime);
    }

    public Anime update(String oldId, Anime anime) {
        return animeService.update(oldId, anime);
    }

    public void delete(Anime anime) {
        animeService.delete(anime);
        animeContributionService.deleteByAnime(anime);
    }

    public Anime getById(String id) {
        return animeService.getById(id);
    }
}
