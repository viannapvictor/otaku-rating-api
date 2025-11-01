package com.otakurating.anime.core.service;

import com.otakurating.anime.core.model.Anime;
import com.otakurating.anime.core.strategy.AnimeSearchStrategy;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AnimeService {
    Page<Anime> getPage(int page, int size, List<AnimeSearchStrategy> strategies);
    Anime add(Anime anime);
    Anime update(String oldId, Anime anime);
    void delete(Anime anime);
    Anime getById(String id);
}
