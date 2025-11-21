package com.otakurating.rating.core.service;

import com.otakurating.rating.core.command.CreateReviewCommand;
import com.otakurating.rating.core.event.RatingEvent;
import com.otakurating.rating.core.event.ReviewEvent;
import com.otakurating.rating.core.exception.RatingNotFoundException;
import com.otakurating.rating.core.exception.ReviewAlreadyReviewedException;
import com.otakurating.rating.core.model.Rating;
import com.otakurating.rating.core.model.Review;
import com.otakurating.rating.core.port.in.CreateReviewUseCase;
import com.otakurating.rating.core.port.out.EventPublisherPort;
import com.otakurating.rating.core.port.out.FindRatingPort;
import com.otakurating.rating.core.port.out.FindReviewPort;
import com.otakurating.rating.core.port.out.SavePort;
import org.springframework.stereotype.Service;

@Service
public class CreateReviewService implements CreateReviewUseCase {
    private final EventPublisherPort eventPublisherPort;
    private final FindRatingPort findRatingPort;
    private final FindReviewPort findReviewPort;
    private final SavePort<Rating> ratingSavePort;
    private final SavePort<Review> reviewSavePort;

    public CreateReviewService(
            EventPublisherPort eventPublisherPort,
            FindRatingPort findRatingPort,
            FindReviewPort findReviewPort,
            SavePort<Rating> ratingSavePort,
            SavePort<Review> reviewSavePort
    ) {
        this.eventPublisherPort = eventPublisherPort;
        this.findRatingPort = findRatingPort;
        this.findReviewPort = findReviewPort;
        this.ratingSavePort = ratingSavePort;
        this.reviewSavePort = reviewSavePort;
    }

    @Override
    public Review create(CreateReviewCommand command) {
        if (findReviewPort.existsByRatingIdAndUserId(command.getRatingId(), command.getUserId())) {
            throw new ReviewAlreadyReviewedException();
        }
        Rating rating = findRatingPort.findById(command.getRatingId()).orElseThrow(RatingNotFoundException::new);
        Review review = new Review(command);

        rating.addReview(review);

        ratingSavePort.save(rating);
        Review createdReview = reviewSavePort.save(review);

        for (ReviewEvent event : review.pullEvents()) {
            eventPublisherPort.publish(event);
        }
        for (RatingEvent event : rating.pullEvents()) {
            eventPublisherPort.publish(event);
        }
        return createdReview;
    }
}
