package com.otaku.rating.core.person.service;

import com.otaku.rating.core.generic.utils.PageUtils;
import com.otaku.rating.core.person.exception.PersonNotFoundException;
import com.otaku.rating.core.person.model.Person;
import com.otaku.rating.core.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    public static final int MAX_PAGE_SIZE = 20;

    private final PersonRepository personRepository;

    @Override
    public Page<Person> getPage(int page, int size) {
        Pageable pageable = PageUtils.createPageable(page, size, MAX_PAGE_SIZE);
        return personRepository.findAnimePage(pageable);
    }

    @Override
    public Person add(Person person) {
        Objects.requireNonNull(person);
        if (person.getId() != null) {
            throw new IllegalArgumentException("The person object cannot have an id when adding.");
        }
        // Authorization is now handled by @RolesAllowed at controller/facade level
        return personRepository.save(person);
    }

    @Override
    public Person update(Person person) {
        Objects.requireNonNull(person);
        if (person.getId() == null) {
            throw new IllegalArgumentException("The person object must have an id when updating.");
        }
        // Authorization is now handled by @RolesAllowed at controller/facade level
        return personRepository.save(person);
    }

    @Override
    public void delete(Person person) {
        Objects.requireNonNull(person);
        if (person.getId() == null) {
            throw new IllegalArgumentException("The person object must have an id when deleting.");
        }
        // Authorization is now handled by @RolesAllowed at controller/facade level
        personRepository.delete(person);
    }

    @Override
    public Person getById(UUID id) {
        return personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }
}
