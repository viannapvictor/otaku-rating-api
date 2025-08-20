package com.otaku.rating.core.person.repository;

import com.otaku.rating.core.person.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository {
    Person save(Person person);
    Optional<Person> findById(UUID id);
    Page<Person> findAnimePage(Pageable pageable);
    void delete(Person person);
}
