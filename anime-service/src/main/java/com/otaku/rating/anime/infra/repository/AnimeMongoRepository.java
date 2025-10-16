package com.otaku.rating.anime.infra.repository;

import com.otaku.rating.anime.infra.persistence.AnimeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnimeMongoRepository extends MongoRepository<AnimeEntity, String> {
}
