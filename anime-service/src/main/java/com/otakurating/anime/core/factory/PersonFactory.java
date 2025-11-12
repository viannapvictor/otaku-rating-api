package com.otakurating.anime.core.factory;

import com.otakurating.anime.core.model.Person;

public interface PersonFactory {
    Person create(PersonParams params);
}
