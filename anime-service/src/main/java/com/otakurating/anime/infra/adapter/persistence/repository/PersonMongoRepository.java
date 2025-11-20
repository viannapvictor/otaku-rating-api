package com.otakurating.anime.infra.adapter.persistence.repository;

import com.otakurating.anime.infra.adapter.persistence.entity.PersonEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PersonMongoRepository extends MongoRepository<PersonEntity, UUID> {
}
