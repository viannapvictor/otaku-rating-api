package com.otaku.rating.infra.anime.repository;

import com.otaku.rating.infra.anime.persistence.AnimeContributionEntity;
import com.otaku.rating.infra.anime.persistence.AnimeContributionIdentifier;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnimeContributionMongoRepository extends MongoRepository<AnimeContributionEntity, AnimeContributionIdentifier> {
}
