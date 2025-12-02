package com.otakurating.anime.infra.adapter.persistence.repository;

import com.otakurating.anime.infra.adapter.persistence.entity.AnimeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface AnimeMongoRepository extends MongoRepository<AnimeEntity, UUID> {
}
