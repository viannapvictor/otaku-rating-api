package com.otakurating.rating.infra.adapter.persistence.repository;

import com.otakurating.rating.infra.adapter.persistence.entity.RatingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RatingMongoRepository extends MongoRepository<RatingEntity, String> {
}
