package com.otakurating.rating.infra.adapter.persistence.adapter;

import com.otakurating.rating.core.model.Review;
import com.otakurating.rating.core.port.out.SavePort;
import com.otakurating.rating.infra.adapter.persistence.entity.ReviewEntity;
import com.otakurating.rating.infra.adapter.persistence.mapper.ReviewInfraMapper;
import com.otakurating.rating.infra.adapter.persistence.repository.ReviewMongoRepository;
import org.springframework.stereotype.Component;

@Component
public class SaveReviewAdapter implements SavePort<Review> {
    private final ReviewMongoRepository reviewMongoRepository;
    private final ReviewInfraMapper mapper;

    public SaveReviewAdapter(ReviewMongoRepository reviewMongoRepository, ReviewInfraMapper mapper) {
        this.reviewMongoRepository = reviewMongoRepository;
        this.mapper = mapper;
    }

    @Override
    public Review save(Review item) {
        ReviewEntity entity = mapper.toEntity(item);
        ReviewEntity savedEntity = reviewMongoRepository.save(entity);

        return mapper.toModel(savedEntity);
    }
}
