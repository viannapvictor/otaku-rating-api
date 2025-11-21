package com.otakurating.rating.core.service;

import com.otakurating.rating.core.command.GetRatingByIdCommand;
import com.otakurating.rating.core.exception.RatingNotFoundException;
import com.otakurating.rating.core.model.Rating;
import com.otakurating.rating.core.port.in.GetRatingByIdUseCase;
import com.otakurating.rating.core.port.out.FindRatingPort;
import org.springframework.stereotype.Service;

@Service
public class GetRatingByIdService implements GetRatingByIdUseCase {
    private final FindRatingPort findRatingPort;

    public GetRatingByIdService(FindRatingPort findRatingPort) {
        this.findRatingPort = findRatingPort;
    }

    @Override
    public Rating getById(GetRatingByIdCommand command) {
        return findRatingPort.findById(command.getId())
                .orElseThrow(RatingNotFoundException::new);
    }
}
