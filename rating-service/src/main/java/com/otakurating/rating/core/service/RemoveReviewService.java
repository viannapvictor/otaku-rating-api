package com.otakurating.rating.core.service;

import com.otakurating.rating.core.command.RemoveReviewCommand;
import com.otakurating.rating.core.event.ReviewEvent;
import com.otakurating.rating.core.exception.ReviewNotFoundException;
import com.otakurating.rating.core.model.Review;
import com.otakurating.rating.core.port.in.RemoveReviewUseCase;
import com.otakurating.rating.core.port.out.DeleteReviewPort;
import com.otakurating.rating.core.port.out.EventPublisherPort;
import com.otakurating.rating.core.port.out.FindReviewPort;
import org.springframework.stereotype.Service;

@Service
public class RemoveReviewService implements RemoveReviewUseCase {
    private final DeleteReviewPort deleteReviewPort;
    private final FindReviewPort findReviewPort;
    private final EventPublisherPort eventPublisherPort;

    public RemoveReviewService(DeleteReviewPort deleteReviewPort, FindReviewPort findReviewPort, EventPublisherPort eventPublisherPort) {
        this.deleteReviewPort = deleteReviewPort;
        this.findReviewPort = findReviewPort;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    public void remove(RemoveReviewCommand command) {
        Review review = findReviewPort.findByRatingIdAndUserId(command.getRatingId(), command.getCurrentUserId())
                .orElseThrow(ReviewNotFoundException::new);

        review.delete();
        deleteReviewPort.deleteByRatingIdAndUserId(command.getRatingId(), command.getCurrentUserId());

        for (ReviewEvent event : review.pullEvents()) {
            eventPublisherPort.publish(event);
        }
    }
}
