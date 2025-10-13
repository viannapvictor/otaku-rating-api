package com.otaku.rating.anime.infra.repository;

import com.otaku.rating.anime.infra.persistence.PersonEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PersonMongoRepository extends MongoRepository<PersonEntity, UUID> {
}
