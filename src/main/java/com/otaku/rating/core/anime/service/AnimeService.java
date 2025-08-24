package com.otaku.rating.core.anime.service;

import com.otaku.rating.core.anime.model.Anime;
import org.springframework.data.domain.Page;

public interface AnimeService {
    Page<Anime> getPage(int page, int size);
    Anime add(Anime anime);
    Anime update(String oldId, Anime anime);
    void delete(Anime anime);
    Anime getById(String id);
}
