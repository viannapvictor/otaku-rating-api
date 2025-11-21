package com.otakurating.rating.core.port.out;

import com.otakurating.rating.core.model.Review;

import java.util.List;
import java.util.Optional;

public interface FindReviewPort {
    Optional<Review> findByRatingIdAndUserId(String ratingId, String userId);
    List<Review> findAllUserReviews(String userId);
    boolean existsByRatingIdAndUserId(String ratingId, String userId);
}
