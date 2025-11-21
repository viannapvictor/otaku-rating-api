package com.otakurating.rating.core.model;

import com.otakurating.rating.core.command.CreateReviewCommand;
import com.otakurating.rating.core.event.ReviewCreatedEvent;
import com.otakurating.rating.core.event.ReviewDeletedEvent;
import com.otakurating.rating.core.event.ReviewEvent;
import com.otakurating.rating.core.event.ReviewUpdatedEvent;
import com.otakurating.rating.core.exception.ReviewMissingRatingIdentifierException;
import com.otakurating.rating.core.exception.ReviewMissingUserIdentifierException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Review {
    private final String ratingId;
    private final String userId;
    private final List<ReviewEvent> events;

    private Rate rate;
    private Comment comment;
    private boolean onDelete;

    public Review(String ratingId, String userId, Rate rate, Comment comment) {
        if (ratingId == null) {
            throw new ReviewMissingRatingIdentifierException();
        }
        if (userId == null) {
            throw new ReviewMissingUserIdentifierException();
        }
        this.ratingId = ratingId;
        this.userId = userId;
        this.rate = Objects.requireNonNull(rate);
        this.comment = Objects.requireNonNull(comment);
        this.onDelete = false;
        this.events = new ArrayList<>();
    }

    public Review(CreateReviewCommand command) {
        if (command.getRatingId() == null) {
            throw new ReviewMissingRatingIdentifierException();
        }
        if (command.getUserId() == null) {
            throw new ReviewMissingUserIdentifierException();
        }
        this.ratingId = command.getRatingId();
        this.userId = command.getUserId();
        this.rate = Rate.valueOf(command.getRate());
        this.comment = Comment.valueOf(command.getComment());
        this.onDelete = false;
        this.events = new ArrayList<>();

        ReviewEvent createdEvent = new ReviewCreatedEvent(
                this.ratingId, this.userId, this.rate.getValue(), this.comment.getValue()
        );
        this.events.add(createdEvent);
    }

    public String getRatingId() {
        return ratingId;
    }

    public String getUserId() {
        return userId;
    }

    public List<ReviewEvent> pullEvents() {
        List<ReviewEvent> pulledEvents = List.copyOf(this.events);
        this.events.clear();

        return pulledEvents;
    }

    public Rate getRate() {
        return rate;
    }

    public Comment getComment() {
        return comment;
    }

    public boolean isOnDelete() {
        return onDelete;
    }

    public void setRate(Rate rate) {
        this.rate = Objects.requireNonNull(rate);
        ReviewEvent createdEvent = new ReviewUpdatedEvent(
                this.ratingId, this.userId, this.rate.getValue(), this.comment.getValue()
        );
        this.events.add(createdEvent);
    }

    public void setComment(Comment comment) {
        this.comment = Objects.requireNonNull(comment);
        ReviewEvent createdEvent = new ReviewUpdatedEvent(
                this.ratingId, this.userId, this.rate.getValue(), this.comment.getValue()
        );
        this.events.add(createdEvent);
    }

    public void delete() {
        this.onDelete = true;
        ReviewEvent createdEvent = new ReviewDeletedEvent(
                this.ratingId, this.userId, this.rate.getValue(), this.comment.getValue()
        );
        this.events.add(createdEvent);
    }
}
