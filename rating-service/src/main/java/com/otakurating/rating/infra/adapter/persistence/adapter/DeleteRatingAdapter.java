package com.otakurating.rating.infra.adapter.persistence.adapter;

import com.otakurating.rating.core.port.out.DeleteRatingPort;
import com.otakurating.rating.infra.adapter.persistence.repository.RatingMongoRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteRatingAdapter implements DeleteRatingPort {
    private final RatingMongoRepository ratingMongoRepository;

    public DeleteRatingAdapter(RatingMongoRepository ratingMongoRepository) {
        this.ratingMongoRepository = ratingMongoRepository;
    }

    @Override
    public void deleteByRatingId(String ratingId) {
        ratingMongoRepository.deleteById(ratingId);
    }
}
