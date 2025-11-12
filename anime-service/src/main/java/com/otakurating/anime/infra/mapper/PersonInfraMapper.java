package com.otakurating.anime.infra.mapper;

import com.otakurating.anime.core.model.Person;
import com.otakurating.anime.core.model.PersonDescription;
import com.otakurating.anime.core.model.PersonName;
import com.otakurating.anime.infra.persistence.PersonEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonInfraMapper {
    public Person toModel(PersonEntity entity) {
        return new Person(
                entity.getId(),
                PersonName.valueOfUnsafe(entity.getName()),
                PersonDescription.valueOfUnsafe(entity.getDescription())
        );
    }

    public PersonEntity toEntity(Person model) {
        return PersonEntity.builder()
                .id(model.getId())
                .name(model.getName().getValue())
                .description(model.getDescription().getValue())
                .build();
    }
}
