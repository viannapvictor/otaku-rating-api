package com.otaku.rating.api.request.person.mapper;

import com.otaku.rating.api.request.person.dto.PersonUpdateDTO;
import com.otaku.rating.core.generic.mapper.InputMapper;
import com.otaku.rating.core.person.factory.PersonFactory;
import com.otaku.rating.core.person.factory.PersonParams;
import com.otaku.rating.core.person.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonUpdateMapper implements InputMapper<Person, PersonUpdateDTO> {
    private final PersonFactory personFactory;

    @Override
    public Person toModel(PersonUpdateDTO entity) {
        PersonParams params = new PersonParams(
                entity.getId(),
                entity.getName(),
                entity.getDescription()
        );
        return personFactory.create(params);
    }
}
