package com.otakurating.anime.app.response.mapper;

import com.otakurating.anime.app.response.dto.PersonViewDTO;
import com.otakurating.anime.core.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonViewMapper {
    public PersonViewDTO toEntity(Person model) {
        return new PersonViewDTO(
                model.getId(),
                model.getName().getValue(),
                model.getDescription().getValue()
        );
    }
}
