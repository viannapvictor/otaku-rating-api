package com.otaku.rating.anime.core.service;

import com.otaku.rating.anime.core.model.Person;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PersonService {
    Page<Person> getPage(int page, int size);
    Person add(User authenticatedUser, Person person);
    Person update(User authenticatedUser, Person person);
    void delete(User authenticatedUser, Person person);
    Person getById(UUID id);
}
