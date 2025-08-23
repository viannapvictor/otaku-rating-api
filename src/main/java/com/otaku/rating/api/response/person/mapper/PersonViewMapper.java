package com.otaku.rating.api.response.person.mapper;

import com.otaku.rating.api.response.person.dto.PersonViewDTO;
import com.otaku.rating.core.generic.mapper.OutputMapper;
import com.otaku.rating.core.person.model.Person;
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
