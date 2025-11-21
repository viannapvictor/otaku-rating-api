package com.otakurating.rating.core.service;

import com.otakurating.rating.core.command.CreateRatingCommand;
import com.otakurating.rating.core.event.RatingEvent;
import com.otakurating.rating.core.exception.RatingAlreadyExistsException;
import com.otakurating.rating.core.model.Rating;
import com.otakurating.rating.core.port.in.CreateRatingUseCase;
import com.otakurating.rating.core.port.out.EventPublisherPort;
import com.otakurating.rating.core.port.out.FindRatingPort;
import com.otakurating.rating.core.port.out.SavePort;
import org.springframework.stereotype.Service;

@Service
public class CreateRatingService implements CreateRatingUseCase {
    private final EventPublisherPort eventPublisherPort;
    private final FindRatingPort findRatingPort;
    private final SavePort<Rating> ratingSavePort;

    public CreateRatingService(EventPublisherPort eventPublisherPort, FindRatingPort findRatingPort, SavePort<Rating> ratingSavePort) {
        this.eventPublisherPort = eventPublisherPort;
        this.findRatingPort = findRatingPort;
        this.ratingSavePort = ratingSavePort;
    }

    @Override
    public Rating create(CreateRatingCommand command) {
        if (findRatingPort.existsById(command.getRatingId())) {
            throw new RatingAlreadyExistsException();
        }
        Rating rating = new Rating(command);
        Rating createdRating = ratingSavePort.save(rating);
        for (RatingEvent event : rating.pullEvents()) {
            eventPublisherPort.publish(event);
        }
        return createdRating;
    }
}
