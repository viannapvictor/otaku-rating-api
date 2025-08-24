package com.otaku.rating.core.anime.model;

import com.otaku.rating.core.person.model.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class AnimeContribution {
    private final Person person;
    private final Anime anime;
    private final CreditRole creditRole;
}
