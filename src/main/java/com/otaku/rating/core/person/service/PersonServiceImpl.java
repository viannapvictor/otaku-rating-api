package com.otaku.rating.core.person.service;

import com.otaku.rating.core.person.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    @Override
    public Page<Person> getPage(int page, int size) {
        return null;
    }

    @Override
    public Person add(Person person) {
        return null;
    }

    @Override
    public Person update(Person person) {
        return null;
    }

    @Override
    public void delete(Person person) {

    }

    @Override
    public Person getById(String id) {
        return null;
    }
}
