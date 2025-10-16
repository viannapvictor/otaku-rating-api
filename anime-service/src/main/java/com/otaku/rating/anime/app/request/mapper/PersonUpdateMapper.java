package com.otaku.rating.anime.app.request.mapper;

import com.otaku.rating.anime.app.request.dto.PersonUpdateDTO;
import com.otaku.rating.anime.core.factory.PersonFactory;
import com.otaku.rating.anime.core.factory.PersonParams;
import com.otaku.rating.anime.core.model.Person;
import com.otaku.rating.generic.core.mapper.InputMapper;
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
