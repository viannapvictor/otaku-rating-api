package com.otaku.rating.anime.core.facade;

import com.otaku.rating.anime.core.model.Person;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PersonFacade {
    Page<Person> getPage(int page, int size);
    Person add(Person person);
    Person update(Person person);
    void delete(Person person);
    Person getById(UUID id);
}
