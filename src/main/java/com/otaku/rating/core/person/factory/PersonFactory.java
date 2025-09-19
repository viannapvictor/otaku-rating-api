package com.otaku.rating.core.person.factory;

import com.otaku.rating.core.person.model.Person;

public interface PersonFactory {
    Person create(PersonParams params);
}
