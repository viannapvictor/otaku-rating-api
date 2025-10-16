package com.otaku.rating.anime.app.request.mapper;

import com.otaku.rating.anime.app.request.dto.PersonCreateDTO;
import com.otaku.rating.anime.core.factory.PersonFactory;
import com.otaku.rating.anime.core.factory.PersonParams;
import com.otaku.rating.anime.core.model.Person;
import com.otaku.rating.generic.core.mapper.InputMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonCreateMapper implements InputMapper<Person, PersonCreateDTO> {
    private final PersonFactory personFactory;

    @Override
    public Person toModel(PersonCreateDTO entity) {
        PersonParams params = new PersonParams(
                null,
                entity.getName(),
                entity.getDescription()
        );
        return personFactory.create(params);
    }
}
