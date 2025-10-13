package com.otakurating.anime.app.request.mapper;

import com.otakurating.anime.app.request.dto.PersonCreateDTO;
import com.otakurating.anime.core.factory.PersonFactory;
import com.otakurating.anime.core.factory.PersonParams;
import com.otakurating.anime.core.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonCreateMapper {
    private final PersonFactory personFactory;

    public Person toModel(PersonCreateDTO entity) {
        PersonParams params = new PersonParams(
                null,
                entity.getName(),
                entity.getDescription()
        );
        return personFactory.create(params);
    }
}
