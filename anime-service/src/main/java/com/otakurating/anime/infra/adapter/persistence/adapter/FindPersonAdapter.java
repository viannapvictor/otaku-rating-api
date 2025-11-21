package com.otakurating.anime.infra.adapter.persistence.adapter;

import com.otakurating.anime.core.model.Person;
import com.otakurating.anime.core.port.out.FindPersonPort;
import com.otakurating.anime.infra.adapter.persistence.mapper.PersonInfraMapper;
import com.otakurating.anime.infra.adapter.persistence.repository.PersonMongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class FindPersonAdapter implements FindPersonPort {
    private final PersonMongoRepository personMongoRepository;
    private final PersonInfraMapper personInfraMapper;

    public FindPersonAdapter(PersonMongoRepository personMongoRepository, PersonInfraMapper personInfraMapper) {
        this.personMongoRepository = personMongoRepository;
        this.personInfraMapper = personInfraMapper;
    }

    @Override
    public Optional<Person> findById(UUID id) {
        return personMongoRepository.findById(id)
                .map(personInfraMapper::toModel);
    }

    @Override
    public Page<Person> findPersonPage(Pageable pageable) {
        return personMongoRepository.findAll(pageable)
                .map(personInfraMapper::toModel);
    }
}
