package com.otakurating.rating.core.service;

import com.otakurating.rating.core.command.RemoveRatingCommand;
import com.otakurating.rating.core.event.RatingEvent;
import com.otakurating.rating.core.exception.RatingNotFoundException;
import com.otakurating.rating.core.model.Rating;
import com.otakurating.rating.core.port.in.RemoveRatingUseCase;
import com.otakurating.rating.core.port.out.DeleteRatingPort;
import com.otakurating.rating.core.port.out.EventPublisherPort;
import com.otakurating.rating.core.port.out.FindRatingPort;
import org.springframework.stereotype.Service;

@Service
public class RemoveRatingService implements RemoveRatingUseCase {
    private final FindRatingPort findRatingPort;
    private final DeleteRatingPort deleteRatingPort;
    private final EventPublisherPort eventPublisherPort;

    public RemoveRatingService(FindRatingPort findRatingPort, DeleteRatingPort deleteRatingPort, EventPublisherPort eventPublisherPort) {
        this.findRatingPort = findRatingPort;
        this.deleteRatingPort = deleteRatingPort;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    public void remove(RemoveRatingCommand command) {
        Rating rating = findRatingPort.findById(command.getRatingId())
                .orElseThrow(RatingNotFoundException::new);
        rating.delete();
        deleteRatingPort.deleteByRatingId(command.getRatingId());
        for (RatingEvent event : rating.pullEvents()) {
            eventPublisherPort.publish(event);
        }
    }
}
