package com.otaku.rating.core.anime.factory;

import com.otaku.rating.core.anime.model.*;
import org.springframework.stereotype.Component;

@Component
public class AnimeFactoryImpl implements AnimeFactory {
    @Override
    public Anime create(AnimeParams params) {
        AnimeTitle title = AnimeTitle.valueOf(params.getTitle());
        AnimeIdentifier id = AnimeIdentifier.valueOf(title);
        AnimeDescription description = AnimeDescription.valueOf(params.getDescription());
        AnimeRelease release = AnimeRelease.valueOf(params.getRelease());

        return new Anime(id, title, description, release);
    }
}
