package com.otakurating.anime.core.factory;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public final class PersonParams {
    private final UUID id;
    private final String name;
    private final String description;
}
