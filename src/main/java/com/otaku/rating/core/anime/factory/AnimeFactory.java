package com.otaku.rating.core.anime.factory;

import com.otaku.rating.core.anime.model.Anime;

public interface AnimeFactory {
    Anime create(AnimeParams params);
}
