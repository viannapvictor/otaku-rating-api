package com.otakurating.rating.infra.adapter.persistence.adapter;

import com.otakurating.rating.core.model.Review;
import com.otakurating.rating.core.port.out.FindReviewPort;
import com.otakurating.rating.infra.adapter.persistence.entity.ReviewEntityIdentifier;
import com.otakurating.rating.infra.adapter.persistence.mapper.ReviewInfraMapper;
import com.otakurating.rating.infra.adapter.persistence.repository.ReviewMongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FindReviewAdapter implements FindReviewPort {
    private final ReviewMongoRepository reviewMongoRepository;
    private final ReviewInfraMapper mapper;

    public FindReviewAdapter(ReviewMongoRepository reviewMongoRepository, ReviewInfraMapper mapper) {
        this.reviewMongoRepository = reviewMongoRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Review> findByRatingIdAndUserId(String ratingId, String userId) {
        ReviewEntityIdentifier id = new ReviewEntityIdentifier(ratingId, userId);
        return reviewMongoRepository.findById(id)
                .map(mapper::toModel);
    }

    @Override
    public List<Review> findAllUserReviews(String userId) {
        return reviewMongoRepository.findAllByIdUserId(userId)
                .stream()
                .map(mapper::toModel)
                .toList();
    }

    @Override
    public boolean existsByRatingIdAndUserId(String ratingId, String userId) {
        ReviewEntityIdentifier id = new ReviewEntityIdentifier(ratingId, userId);
        return reviewMongoRepository.existsById(id);
    }
}
