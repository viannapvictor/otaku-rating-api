package com.otaku.rating.anime.app.response.mapper;

import com.otaku.rating.anime.app.response.dto.PersonViewDTO;
import com.otaku.rating.anime.core.model.Person;
import com.otaku.rating.generic.core.mapper.OutputMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonViewMapper implements OutputMapper<Person, PersonViewDTO> {
    @Override
    public PersonViewDTO toEntity(Person model) {
        return new PersonViewDTO(
                model.getId(),
                model.getName().getValue(),
                model.getDescription().getValue()
        );
    }
}
