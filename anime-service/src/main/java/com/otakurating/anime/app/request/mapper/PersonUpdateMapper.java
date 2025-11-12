package com.otakurating.anime.app.request.mapper;

import com.otakurating.anime.app.request.dto.PersonUpdateDTO;
import com.otakurating.anime.core.factory.PersonFactory;
import com.otakurating.anime.core.factory.PersonParams;
import com.otakurating.anime.core.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonUpdateMapper {
    private final PersonFactory personFactory;

    public Person toModel(PersonUpdateDTO entity) {
        PersonParams params = new PersonParams(
                entity.getId(),
                entity.getName(),
                entity.getDescription()
        );
        return personFactory.create(params);
    }
}
