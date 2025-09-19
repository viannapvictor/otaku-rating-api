package com.otaku.rating.core.person.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public final class Person {
    private final UUID id;
    private final PersonName name;
    private final PersonDescription description;
}
