package com.otaku.rating.anime.core.factory;

import com.otaku.rating.anime.core.model.Person;
import com.otaku.rating.anime.core.model.PersonDescription;
import com.otaku.rating.anime.core.model.PersonName;
import org.springframework.stereotype.Component;

@Component
public class PersonFactoryImpl implements PersonFactory {
    @Override
    public Person create(PersonParams params) {
        PersonName name = PersonName.valueOf(params.getName());
        PersonDescription description = PersonDescription.valueOf(params.getDescription());

        return new Person(params.getId(), name, description);
    }
}
