package com.otaku.rating.core.person.model;

import com.otaku.rating.core.anime.model.AnimeContribution;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public final class Person {
    private final Long id;
    private final PersonName name;
    private final PersonDescription description;
    private final List<AnimeContribution> contributions;
}
