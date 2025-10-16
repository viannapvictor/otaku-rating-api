package com.otaku.rating.anime.core.service;

import com.otaku.rating.anime.core.exception.PersonNotFoundException;
import com.otaku.rating.anime.core.model.Person;
import com.otaku.rating.anime.core.repository.PersonRepository;
import com.otaku.rating.generic.core.exception.ForbiddenException;
import com.otaku.rating.generic.core.utils.PageUtils;
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
    public Person add(User authenticatedUser, Person person) {
        Objects.requireNonNull(authenticatedUser);
        Objects.requireNonNull(person);
        if (person.getId() != null) {
            throw new IllegalArgumentException("The person object cannot have an id when adding.");
        }
        if (!authenticatedUser.hasAuthorization(UserAuthorizationLevel.MODIFICATION)) {
            throw new ForbiddenException();
        }
        return personRepository.save(person);
    }

    @Override
    public Person update(User authenticatedUser, Person person) {
        Objects.requireNonNull(authenticatedUser);
        Objects.requireNonNull(person);
        if (person.getId() == null) {
            throw new IllegalArgumentException("The person object must have an id when updating.");
        }
        if (!authenticatedUser.hasAuthorization(UserAuthorizationLevel.MODIFICATION)) {
            throw new ForbiddenException();
        }
        return personRepository.save(person);
    }

    @Override
    public void delete(User authenticatedUser, Person person) {
        Objects.requireNonNull(authenticatedUser);
        Objects.requireNonNull(person);
        if (person.getId() == null) {
            throw new IllegalArgumentException("The person object must have an id when deleting.");
        }
        if (!authenticatedUser.hasAuthorization(UserAuthorizationLevel.MODIFICATION)) {
            throw new ForbiddenException();
        }
        personRepository.delete(person);
    }

    @Override
    public Person getById(UUID id) {
        return personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }
}
