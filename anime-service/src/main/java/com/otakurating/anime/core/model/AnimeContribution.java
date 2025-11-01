package com.otakurating.anime.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public final class AnimeContribution {
    private final UUID personId;
    private final String animeId;
    private final CreditRole creditRole;
}
