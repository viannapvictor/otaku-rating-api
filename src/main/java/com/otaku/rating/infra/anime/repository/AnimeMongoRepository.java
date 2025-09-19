package com.otaku.rating.infra.anime.repository;

import com.otaku.rating.infra.anime.persistence.AnimeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnimeMongoRepository extends MongoRepository<AnimeEntity, String> {
}
