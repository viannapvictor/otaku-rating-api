package com.otaku.rating.core.anime.facade;

import com.otaku.rating.core.anime.model.Anime;
import com.otaku.rating.core.anime.service.AnimeContributionService;
import com.otaku.rating.core.anime.service.AnimeService;
import com.otaku.rating.core.anime.strategy.AnimeSearchStrategy;
import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.service.ContextService;
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
