package com.otakurating.rating.core.port.in;

import com.otakurating.rating.core.command.CreateRatingCommand;
import com.otakurating.rating.core.model.Rating;

public interface CreateRatingUseCase {
    Rating create(CreateRatingCommand command);
}
