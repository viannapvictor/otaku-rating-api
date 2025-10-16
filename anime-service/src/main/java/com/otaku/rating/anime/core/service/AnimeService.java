package com.otaku.rating.anime.core.service;

import com.otaku.rating.anime.core.model.Anime;
import com.otaku.rating.anime.core.strategy.AnimeSearchStrategy;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AnimeService {
    Page<Anime> getPage(int page, int size, List<AnimeSearchStrategy> strategies);
    Anime add(User authenticatedUser, Anime anime);
    Anime update(User authenticatedUser, String oldId, Anime anime);
    void delete(User authenticatedUser, Anime anime);
    Anime getById(String id);
}
