package com.otakurating.anime.infra.adapter.persistence.mapper;

import com.otakurating.anime.core.model.Person;
import com.otakurating.anime.core.model.PersonDescription;
import com.otakurating.anime.core.model.PersonName;
import com.otakurating.anime.infra.adapter.persistence.entity.PersonEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonInfraMapper {
    public Person toModel(PersonEntity entity) {
        PersonName name = PersonName.valueOf(entity.name());
        PersonDescription description = PersonDescription.valueOf(entity.description());

        return new Person(entity.id(), name, description);
    }

    public PersonEntity toEntity(Person model) {
        return new PersonEntity(
                model.getId(),
                model.getName().getValue(),
                model.getDescription().getValue()
        );
    }
}
