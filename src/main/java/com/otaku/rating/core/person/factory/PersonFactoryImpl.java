package com.otaku.rating.core.person.factory;

import com.otaku.rating.core.person.model.Person;
import com.otaku.rating.core.person.model.PersonDescription;
import com.otaku.rating.core.person.model.PersonName;
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
