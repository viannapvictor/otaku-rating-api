package com.otaku.rating.core.anime.factory;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public final class AnimeParams {
    private final String title;
    private final String description;
    private final LocalDate release;
}
