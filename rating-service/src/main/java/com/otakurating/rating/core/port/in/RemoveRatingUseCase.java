package com.otakurating.rating.core.port.in;

import com.otakurating.rating.core.command.RemoveRatingCommand;

public interface RemoveRatingUseCase {
    void remove(RemoveRatingCommand command);
}
