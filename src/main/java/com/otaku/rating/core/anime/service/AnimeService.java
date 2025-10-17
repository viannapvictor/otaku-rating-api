package com.otaku.rating.core.anime.service;

import com.otaku.rating.core.anime.model.Anime;
import com.otaku.rating.core.anime.strategy.AnimeSearchStrategy;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AnimeService {
    Page<Anime> getPage(int page, int size, List<AnimeSearchStrategy> strategies);
    Anime add(Anime anime);
    Anime update(String oldId, Anime anime);
    void delete(Anime anime);
    Anime getById(String id);
}
