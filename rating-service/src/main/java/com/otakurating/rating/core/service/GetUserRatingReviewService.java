package com.otakurating.rating.core.service;

import com.otakurating.rating.core.command.GetUserRatingReviewCommand;
import com.otakurating.rating.core.exception.ReviewNotFoundException;
import com.otakurating.rating.core.model.Review;
import com.otakurating.rating.core.port.in.GetUserRatingReviewUseCase;
import com.otakurating.rating.core.port.out.FindReviewPort;
import org.springframework.stereotype.Service;

@Service
public class GetUserRatingReviewService implements GetUserRatingReviewUseCase {
    private final FindReviewPort findReviewPort;

    public GetUserRatingReviewService(FindReviewPort findReviewPort) {
        this.findReviewPort = findReviewPort;
    }

    @Override
    public Review getById(GetUserRatingReviewCommand command) {
        return findReviewPort.findByRatingIdAndUserId(command.getRatingId(), command.getUserId())
                .orElseThrow(ReviewNotFoundException::new);
    }
}
