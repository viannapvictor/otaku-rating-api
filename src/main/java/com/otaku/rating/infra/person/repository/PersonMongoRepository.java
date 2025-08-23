package com.otaku.rating.infra.person.repository;

import com.otaku.rating.infra.person.persistence.PersonEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PersonMongoRepository extends MongoRepository<PersonEntity, UUID> {
}
