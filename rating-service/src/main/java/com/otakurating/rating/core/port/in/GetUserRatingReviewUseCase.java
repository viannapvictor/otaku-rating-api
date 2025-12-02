package com.otakurating.rating.core.port.in;

import com.otakurating.rating.core.command.GetUserRatingReviewCommand;
import com.otakurating.rating.core.model.Review;

public interface GetUserRatingReviewUseCase {
    Review getById(GetUserRatingReviewCommand command);
}
