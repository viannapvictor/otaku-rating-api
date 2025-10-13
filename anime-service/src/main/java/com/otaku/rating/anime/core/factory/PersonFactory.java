package com.otaku.rating.anime.core.factory;

import com.otaku.rating.anime.core.model.Person;

public interface PersonFactory {
    Person create(PersonParams params);
}
