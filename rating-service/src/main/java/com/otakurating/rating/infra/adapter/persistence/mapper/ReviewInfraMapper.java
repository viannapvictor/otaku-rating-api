package com.otakurating.rating.infra.adapter.persistence.mapper;

import com.otakurating.rating.core.model.Comment;
import com.otakurating.rating.core.model.Rate;
import com.otakurating.rating.core.model.Review;
import com.otakurating.rating.infra.adapter.persistence.entity.ReviewEntity;
import com.otakurating.rating.infra.adapter.persistence.entity.ReviewEntityIdentifier;
import org.springframework.stereotype.Component;

@Component
public class ReviewInfraMapper {
    public ReviewEntity toEntity(Review model) {
        ReviewEntityIdentifier id = new ReviewEntityIdentifier(model.getRatingId(), model.getUserId());
        return new ReviewEntity(id, model.getRate().getValue(), model.getComment().getValue());
    }

    public Review toModel(ReviewEntity entity) {
        Rate rate = Rate.valueOf(entity.rate());
        Comment comment = Comment.valueOf(entity.comment());

        return new Review(entity.id().ratingId(), entity.id().userId(), rate, comment);
    }
}
