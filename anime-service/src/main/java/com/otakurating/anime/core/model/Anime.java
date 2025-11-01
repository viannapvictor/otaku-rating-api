package com.otakurating.anime.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class Anime {
    private final AnimeIdentifier id;
    private final AnimeTitle title;
    private final AnimeDescription description;
    private final AnimeRelease release;
}
