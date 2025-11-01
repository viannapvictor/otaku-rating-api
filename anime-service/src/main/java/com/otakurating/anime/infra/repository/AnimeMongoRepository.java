package com.otakurating.anime.infra.repository;

import com.otakurating.anime.infra.persistence.AnimeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnimeMongoRepository extends MongoRepository<AnimeEntity, String> {
}
