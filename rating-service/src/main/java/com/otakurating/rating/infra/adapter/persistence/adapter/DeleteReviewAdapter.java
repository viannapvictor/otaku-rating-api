package com.otakurating.rating.infra.adapter.persistence.adapter;

import com.otakurating.rating.core.port.out.DeleteReviewPort;
import com.otakurating.rating.infra.adapter.persistence.entity.ReviewEntityIdentifier;
import com.otakurating.rating.infra.adapter.persistence.repository.ReviewMongoRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteReviewAdapter implements DeleteReviewPort {
    private final ReviewMongoRepository reviewMongoRepository;

    public DeleteReviewAdapter(ReviewMongoRepository reviewMongoRepository) {
        this.reviewMongoRepository = reviewMongoRepository;
    }

    @Override
    public void deleteAllByUserId(String userId) {
        reviewMongoRepository.deleteAllByIdUserId(userId);
    }

    @Override
    public void deleteByRatingIdAndUserId(String ratingId, String userId) {
        ReviewEntityIdentifier id = new ReviewEntityIdentifier(ratingId, userId);
        reviewMongoRepository.deleteById(id);
    }
}
