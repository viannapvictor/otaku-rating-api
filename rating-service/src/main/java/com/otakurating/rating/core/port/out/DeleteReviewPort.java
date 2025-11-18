package com.otakurating.rating.core.port.out;

public interface DeleteReviewPort {
    void deleteAllByUserId(String userId);
    void deleteByRatingIdAndUserId(String ratingId, String userId);
}
