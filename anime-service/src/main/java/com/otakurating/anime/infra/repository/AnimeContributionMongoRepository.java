package com.otakurating.anime.infra.repository;

import com.otakurating.anime.infra.persistence.AnimeContributionEntity;
import com.otakurating.anime.infra.persistence.AnimeContributionIdentifier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface AnimeContributionMongoRepository extends MongoRepository<AnimeContributionEntity, AnimeContributionIdentifier> {
    void deleteByIdAnimeId(String animeId);
    void deleteByIdPersonId(UUID personId);
}
