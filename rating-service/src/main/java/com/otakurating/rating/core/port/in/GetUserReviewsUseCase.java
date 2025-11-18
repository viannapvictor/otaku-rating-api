package com.otakurating.rating.core.port.in;

import com.otakurating.rating.core.command.GetUserReviewsCommand;
import com.otakurating.rating.core.model.Review;

import java.util.List;

public interface GetUserReviewsUseCase {
    List<Review> getAll(GetUserReviewsCommand command);
}
