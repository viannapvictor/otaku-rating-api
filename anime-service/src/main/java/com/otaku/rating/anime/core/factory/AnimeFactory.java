package com.otaku.rating.anime.core.factory;

import com.otaku.rating.anime.core.model.Anime;

public interface AnimeFactory {
    Anime create(AnimeParams params);
}
