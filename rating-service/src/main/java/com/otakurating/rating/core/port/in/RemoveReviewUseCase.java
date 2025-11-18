package com.otakurating.rating.core.port.in;

import com.otakurating.rating.core.command.RemoveReviewCommand;

public interface RemoveReviewUseCase {
    void remove(RemoveReviewCommand command);
}
