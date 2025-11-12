package com.otakurating.anime.core.service;

import com.otakurating.anime.core.model.Person;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PersonService {
    Page<Person> getPage(int page, int size);
    Person add(Person person);
    Person update(Person person);
    void delete(Person person);
    Person getById(UUID id);
}
