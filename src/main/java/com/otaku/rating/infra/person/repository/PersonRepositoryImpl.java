package com.otaku.rating.infra.person.repository;

import com.otaku.rating.core.generic.mapper.Mapper;
import com.otaku.rating.core.person.model.Person;
import com.otaku.rating.core.person.repository.PersonRepository;
import com.otaku.rating.infra.person.persistence.PersonEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {
    private final PersonMongoRepository personMongoRepository;
    private final Mapper<Person, PersonEntity> personEntityMapper;

    @Override
    public Person save(Person person) {
        PersonEntity entity = personEntityMapper.toEntity(person);
        PersonEntity savedEntity = personMongoRepository.save(entity);
        return personEntityMapper.toModel(savedEntity);
    }

    @Override
    public Optional<Person> findById(UUID id) {
        return personMongoRepository.findById(id)
                .map(personEntityMapper::toModel);
    }

    @Override
    public Page<Person> findAnimePage(Pageable pageable) {
        return personMongoRepository.findAll(pageable)
                .map(personEntityMapper::toModel);
    }

    @Override
    public void delete(Person person) {
        personMongoRepository.deleteById(person.getId());
    }
}
