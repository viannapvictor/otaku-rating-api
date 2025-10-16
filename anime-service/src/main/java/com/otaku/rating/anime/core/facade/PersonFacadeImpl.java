package com.otaku.rating.anime.core.facade;

import com.otaku.rating.anime.core.model.Person;
import com.otaku.rating.anime.core.service.AnimeContributionService;
import com.otaku.rating.anime.core.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PersonFacadeImpl implements PersonFacade {
    private final PersonService personService;
    private AnimeContributionService animeContributionService;
    private final ContextService contextService;

    @Override
    public Page<Person> getPage(int page, int size) {
        return personService.getPage(page, size);
    }

    @Override
    public Person add(Person person) {
        User user = contextService.getUserOrThrow();
        return personService.add(user, person);
    }

    @Override
    public Person update(Person person) {
        User user = contextService.getUserOrThrow();
        return personService.update(user, person);
    }

    @Override
    public void delete(Person person) {
        User user = contextService.getUserOrThrow();
        personService.delete(user, person);
        animeContributionService.deleteByPerson(user, person);
    }

    @Override
    public Person getById(UUID id) {
        return personService.getById(id);
    }
}
