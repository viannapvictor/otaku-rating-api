package com.otaku.rating.api.request.person.mapper;

import com.otaku.rating.api.request.person.dto.PersonCreateDTO;
import com.otaku.rating.core.generic.mapper.InputMapper;
import com.otaku.rating.core.person.factory.PersonFactory;
import com.otaku.rating.core.person.factory.PersonParams;
import com.otaku.rating.core.person.model.Person;
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
