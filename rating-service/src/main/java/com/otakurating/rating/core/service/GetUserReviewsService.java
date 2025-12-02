package com.otakurating.rating.core.service;

import com.otakurating.rating.core.command.GetUserReviewsCommand;
import com.otakurating.rating.core.model.Review;
import com.otakurating.rating.core.port.in.GetUserReviewsUseCase;
import com.otakurating.rating.core.port.out.FindReviewPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserReviewsService implements GetUserReviewsUseCase {
    private final FindReviewPort findReviewPort;

    public GetUserReviewsService(FindReviewPort findReviewPort) {
        this.findReviewPort = findReviewPort;
    }

    @Override
    public List<Review> getAll(GetUserReviewsCommand command) {
        return findReviewPort.findAllUserReviews(command.getUserId());
    }
}
