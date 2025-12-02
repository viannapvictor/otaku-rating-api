package com.otakurating.rating.core.model;

import com.otakurating.rating.core.command.CreateRatingCommand;
import com.otakurating.rating.core.command.UpdateRatingIdCommand;
import com.otakurating.rating.core.event.*;
import com.otakurating.rating.core.exception.RatingMissingIdentifierException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Rating {
    private final List<RatingEvent> events;

    private String id;
    private long totalRate;
    private int reviewsCount;
    private boolean onDelete;

    public Rating(String id, long totalRate, int reviewsCount) {
        if (id == null) {
            throw new RatingMissingIdentifierException();
        }
        this.id = id;
        this.totalRate = totalRate;
        this.reviewsCount = reviewsCount;
        this.onDelete = false;
        this.events = new ArrayList<>();
    }

    public Rating(CreateRatingCommand command) {
        if (command.getRatingId() == null) {
            throw new RatingMissingIdentifierException();
        }
        this.id = command.getRatingId();
        this.totalRate = 0;
        this.reviewsCount = 0;
        this.onDelete = false;
        this.events = new ArrayList<>();

        RatingEvent createdEvent = new RatingCreatedEvent(this.id, this.totalRate, this.reviewsCount);
        this.events.add(createdEvent);
    }

    public void addReview(Review review) {
        if (!Objects.equals(this.id, review.getRatingId())) {
            return;
        }
        this.reviewsCount += 1;
        this.totalRate += review.getRate().getValue();

        RatingEvent createdEvent = new RatingUpdatedEvent(this.id, this.totalRate, this.reviewsCount);
        this.events.add(createdEvent);
    }

    public void removeReview(Review review) {
        if (!Objects.equals(this.id, review.getRatingId()) || this.reviewsCount == 0) {
            return;
        }
        if (!review.isOnDelete()) {
            return;
        }
        this.reviewsCount -= 1;
        this.totalRate -= review.getRate().getValue();

        RatingEvent createdEvent = new RatingUpdatedEvent(this.id, this.totalRate, this.reviewsCount);
        this.events.add(createdEvent);
    }

    public void delete() {
        this.onDelete = true;
        RatingEvent createdEvent = new RatingDeletedEvent(this.id, this.totalRate, this.reviewsCount);
        this.events.add(createdEvent);
    }

    public void updateId(UpdateRatingIdCommand command) {
        if (!Objects.equals(command.getCurrentRatingId(), id)) {
            return;
        }
        if (command.getNewRatingId() == null) {
            throw new RatingMissingIdentifierException();
        }
        this.id = command.getNewRatingId();
    }

    public String getId() {
        return id;
    }

    public List<RatingEvent> pullEvents() {
        List<RatingEvent> pulledEvents = List.copyOf(this.events);
        this.events.clear();

        return pulledEvents;
    }

    public Float getAverageRate() {
        if (reviewsCount == 0) {
            return null;
        }
        return (float)totalRate / reviewsCount;
    }

    public long getTotalRate() {
        return totalRate;
    }

    public int getReviewsCount() {
        return reviewsCount;
    }

    public boolean isOnDelete() {
        return onDelete;
    }
}
