package com.otakurating.rating.infra.adapter.persistence.repository;

import com.otakurating.rating.infra.adapter.persistence.entity.ReviewEntity;
import com.otakurating.rating.infra.adapter.persistence.entity.ReviewEntityIdentifier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewMongoRepository extends MongoRepository<ReviewEntity, ReviewEntityIdentifier> {
    List<ReviewEntity> findAllByIdUserId(String userId);
    void deleteAllByIdUserId(String userId);
}
