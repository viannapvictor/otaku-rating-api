package com.otaku.rating.infra.person.mapper;

import com.otaku.rating.core.generic.mapper.Mapper;
import com.otaku.rating.core.person.model.Person;
import com.otaku.rating.core.person.model.PersonDescription;
import com.otaku.rating.core.person.model.PersonName;
import com.otaku.rating.infra.person.persistence.PersonEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonInfraMapper implements Mapper<Person, PersonEntity> {
    @Override
    public Person toModel(PersonEntity entity) {
        return new Person(
                entity.getId(),
                PersonName.valueOfUnsafe(entity.getName()),
                PersonDescription.valueOfUnsafe(entity.getDescription())
        );
    }

    @Override
    public PersonEntity toEntity(Person model) {
        return PersonEntity.builder()
                .id(model.getId())
                .name(model.getName().getValue())
                .description(model.getDescription().getValue())
                .build();
    }
}
