package com.otakurating.anime.core.factory;

import com.otakurating.anime.core.model.Person;
import com.otakurating.anime.core.model.PersonDescription;
import com.otakurating.anime.core.model.PersonName;
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
