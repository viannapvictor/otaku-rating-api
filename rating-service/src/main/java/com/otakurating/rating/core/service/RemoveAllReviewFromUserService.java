package com.otakurating.rating.core.service;

import com.otakurating.rating.core.command.RemoveAllReviewFromUserCommand;
import com.otakurating.rating.core.event.UserReviewsDeletedEvent;
import com.otakurating.rating.core.exception.RatingNotFoundException;
import com.otakurating.rating.core.model.Rating;
import com.otakurating.rating.core.model.Review;
import com.otakurating.rating.core.port.in.RemoveAllReviewFromUserUseCase;
import com.otakurating.rating.core.port.out.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemoveAllReviewFromUserService implements RemoveAllReviewFromUserUseCase {
    private final EventPublisherPort eventPublisherPort;
    private final FindRatingPort findRatingPort;
    private final FindReviewPort findReviewPort;
    private final SavePort<Rating> ratingSavePort;
    private final DeleteReviewPort deleteReviewPort;

    public RemoveAllReviewFromUserService(EventPublisherPort eventPublisherPort, FindRatingPort findRatingPort, FindReviewPort findReviewPort, SavePort<Rating> ratingSavePort, DeleteReviewPort deleteReviewPort) {
        this.eventPublisherPort = eventPublisherPort;
        this.findRatingPort = findRatingPort;
        this.findReviewPort = findReviewPort;
        this.ratingSavePort = ratingSavePort;
        this.deleteReviewPort = deleteReviewPort;
    }

    @Override
    public void remove(RemoveAllReviewFromUserCommand command) {
        List<Review> userReviews = findReviewPort.findAllUserReviews(command.getUserId());
        if (userReviews.isEmpty()) {
            return;
        }
        for (Review review : userReviews) {
            Rating rating = findRatingPort.findById(review.getRatingId())
                    .orElseThrow(RatingNotFoundException::new);
            review.delete();
            rating.removeReview(review);
            ratingSavePort.save(rating);
        }
        deleteReviewPort.deleteAllByUserId(command.getUserId());

        UserReviewsDeletedEvent event = new UserReviewsDeletedEvent(command.getUserId());
        eventPublisherPort.publish(event);
    }
}
