package com.otakurating.rating.core.port.in;

import com.otakurating.rating.core.command.CreateReviewCommand;
import com.otakurating.rating.core.model.Review;

public interface CreateReviewUseCase {
    Review create(CreateReviewCommand command);
}
