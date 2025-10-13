package com.otaku.rating.anime.infra.mapper;

import com.otaku.rating.anime.core.model.Person;
import com.otaku.rating.anime.core.model.PersonDescription;
import com.otaku.rating.anime.core.model.PersonName;
import com.otaku.rating.anime.infra.persistence.PersonEntity;
import com.otaku.rating.generic.core.mapper.Mapper;
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
