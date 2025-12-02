package com.otakurating.rating.core.port.in;

import com.otakurating.rating.core.command.GetRatingByIdCommand;
import com.otakurating.rating.core.model.Rating;

public interface GetRatingByIdUseCase {
    Rating getById(GetRatingByIdCommand command);
}
