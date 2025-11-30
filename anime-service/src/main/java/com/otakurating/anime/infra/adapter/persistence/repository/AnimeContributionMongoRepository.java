package com.otakurating.anime.infra.adapter.persistence.repository;

import com.otakurating.anime.infra.adapter.persistence.entity.AnimeContributionEntity;
import com.otakurating.anime.infra.adapter.persistence.entity.AnimeContributionIdentifier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface AnimeContributionMongoRepository extends MongoRepository<AnimeContributionEntity, AnimeContributionIdentifier> {
    void deleteByIdAnimeId(UUID animeId);
    void deleteByIdPersonId(UUID personId);
    List<AnimeContributionEntity> findByIdAnimeId(UUID animeId);
}
