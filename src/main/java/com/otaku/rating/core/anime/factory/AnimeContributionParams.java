package com.otaku.rating.core.anime.factory;

import com.otaku.rating.core.anime.model.CreditRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class AnimeContributionParams {
    private final Long personId;
    private final CreditRole role;
}
