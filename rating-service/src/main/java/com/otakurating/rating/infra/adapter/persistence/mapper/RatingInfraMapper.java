package com.otakurating.rating.infra.adapter.persistence.mapper;

import com.otakurating.rating.core.model.Rating;
import com.otakurating.rating.infra.adapter.persistence.entity.RatingEntity;
import org.springframework.stereotype.Component;

@Component
public class RatingInfraMapper {
    public RatingEntity toEntity(Rating model) {
        return new RatingEntity(model.getId(), model.getTotalRate(), model.getReviewsCount());
    }

    public Rating toModel(RatingEntity entity) {
        return new Rating(entity.id(), entity.totalRate(), entity.reviewsCount());
    }
}
