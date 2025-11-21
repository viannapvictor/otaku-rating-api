package com.otakurating.anime.infra.adapter.persistence.adapter;

import com.otakurating.anime.core.model.Person;
import com.otakurating.anime.core.port.out.SavePort;
import com.otakurating.anime.infra.adapter.persistence.entity.PersonEntity;
import com.otakurating.anime.infra.adapter.persistence.mapper.PersonInfraMapper;
import com.otakurating.anime.infra.adapter.persistence.repository.PersonMongoRepository;
import org.springframework.stereotype.Component;

@Component
public class SavePersonAdapter implements SavePort<Person> {
    private final PersonMongoRepository personMongoRepository;
    private final PersonInfraMapper personInfraMapper;

    public SavePersonAdapter(PersonMongoRepository personMongoRepository, PersonInfraMapper personInfraMapper) {
        this.personMongoRepository = personMongoRepository;
        this.personInfraMapper = personInfraMapper;
    }

    @Override
    public Person save(Person item) {
        PersonEntity entity = personInfraMapper.toEntity(item);
        PersonEntity savedEntity = personMongoRepository.save(entity);

        return personInfraMapper.toModel(savedEntity);
    }
}
