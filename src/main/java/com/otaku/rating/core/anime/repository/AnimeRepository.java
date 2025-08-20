package com.otaku.rating.core.anime.repository;

import com.otaku.rating.core.anime.model.Anime;

public interface AnimeRepository {
    Anime save(Anime anime);
    boolean exists(String id);
    void delete(Anime anime);
}
