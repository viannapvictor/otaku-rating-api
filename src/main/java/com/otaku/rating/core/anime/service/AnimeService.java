package com.otaku.rating.core.anime.service;

import com.otaku.rating.core.anime.model.Anime;
import com.otaku.rating.core.anime.strategy.AnimeSearchStrategy;
import com.otaku.rating.core.user.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AnimeService {
    Page<Anime> getPage(int page, int size, List<AnimeSearchStrategy> strategies);
    Anime add(User authenticatedUser, Anime anime);
    Anime update(User authenticatedUser, String oldId, Anime anime);
    void delete(User authenticatedUser, Anime anime);
    Anime getById(String id);
}
