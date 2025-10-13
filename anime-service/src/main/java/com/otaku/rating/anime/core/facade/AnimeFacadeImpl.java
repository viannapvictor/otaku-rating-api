package com.otaku.rating.anime.core.facade;

import com.otaku.rating.anime.core.model.Anime;
import com.otaku.rating.anime.core.service.AnimeContributionService;
import com.otaku.rating.anime.core.service.AnimeService;
import com.otaku.rating.anime.core.strategy.AnimeSearchStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AnimeFacadeImpl implements AnimeFacade {
    private final AnimeService animeService;
    private final AnimeContributionService animeContributionService;
    private final ContextService contextService;

    public Page<Anime> getPage(int page, int size, List<AnimeSearchStrategy> strategies) {
        return animeService.getPage(page, size, strategies);
    }

    public Anime add(Anime anime) {
        User user = contextService.getUserOrThrow();
        return animeService.add(user, anime);
    }

    public Anime update(String oldId, Anime anime) {
        User user = contextService.getUserOrThrow();
        return animeService.update(user, oldId, anime);
    }

    public void delete(Anime anime) {
        User user = contextService.getUserOrThrow();
        animeService.delete(user, anime);
        animeContributionService.deleteByAnime(user, anime);
    }

    public Anime getById(String id) {
        return animeService.getById(id);
    }
}
