package com.otaku.rating.core.person.facade;

import com.otaku.rating.core.person.model.Person;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PersonFacade {
    Page<Person> getPage(int page, int size);
    Person add(Person person);
    Person update(Person person);
    void delete(Person person);
    Person getById(UUID id);
}
