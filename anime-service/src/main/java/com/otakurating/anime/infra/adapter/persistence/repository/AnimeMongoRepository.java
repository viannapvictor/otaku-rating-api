package com.otakurating.anime.infra.adapter.persistence.repository;

import com.otakurating.anime.infra.adapter.persistence.entity.AnimeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnimeMongoRepository extends MongoRepository<AnimeEntity, String> {
}
