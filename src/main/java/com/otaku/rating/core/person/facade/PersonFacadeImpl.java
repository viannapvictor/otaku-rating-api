package com.otaku.rating.core.person.facade;

import com.otaku.rating.core.anime.service.AnimeContributionService;
import com.otaku.rating.core.person.model.Person;
import com.otaku.rating.core.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PersonFacadeImpl implements PersonFacade {
    private final PersonService personService;
    private final AnimeContributionService animeContributionService;

    @Override
    public Page<Person> getPage(int page, int size) {
        return personService.getPage(page, size);
    }

    @Override
    public Person add(Person person) {
        return personService.add(person);
    }

    @Override
    public Person update(Person person) {
        return personService.update(person);
    }

    @Override
    public void delete(Person person) {
        personService.delete(person);
        animeContributionService.deleteByPerson(person);
    }

    @Override
    public Person getById(UUID id) {
        return personService.getById(id);
    }
}
