package com.otakurating.anime.infra.repository;

import com.otakurating.anime.infra.persistence.PersonEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PersonMongoRepository extends MongoRepository<PersonEntity, UUID> {
}
