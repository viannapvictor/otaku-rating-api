package com.otakurating.anime.core.factory;

import com.otakurating.anime.core.model.Anime;

public interface AnimeFactory {
    Anime create(AnimeParams params);
}
