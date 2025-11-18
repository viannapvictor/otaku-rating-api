package com.otakurating.rating.core.port.out;

import com.otakurating.rating.core.model.Rating;

import java.util.Optional;

public interface FindRatingPort {
    Optional<Rating> findById(String id);
    boolean existsById(String id);
}
