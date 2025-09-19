package com.otaku.rating.core.person.service;

import com.otaku.rating.core.person.model.Person;
import com.otaku.rating.core.user.model.User;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PersonService {
    Page<Person> getPage(int page, int size);
    Person add(User authenticatedUser, Person person);
    Person update(User authenticatedUser, Person person);
    void delete(User authenticatedUser, Person person);
    Person getById(UUID id);
}
