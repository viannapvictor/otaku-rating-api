package com.otaku.rating.core.anime.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public final class Anime {
    private final AnimeIdentifier id;
    private final AnimeTitle title;
    private final AnimeDescription description;
    private final AnimeRelease release;
    private final List<AnimeContribution> contributions;
}
