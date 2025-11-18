package com.otakurating.rating.core.port.in;

import com.otakurating.rating.core.command.RemoveAllReviewFromUserCommand;

public interface RemoveAllReviewFromUserUseCase {
    void remove(RemoveAllReviewFromUserCommand command);
}
