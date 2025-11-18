package com.otakurating.rating.infra.adapter.persistence.adapter;

import com.otakurating.rating.core.model.Rating;
import com.otakurating.rating.core.port.out.SavePort;
import com.otakurating.rating.infra.adapter.persistence.entity.RatingEntity;
import com.otakurating.rating.infra.adapter.persistence.mapper.RatingInfraMapper;
import com.otakurating.rating.infra.adapter.persistence.repository.RatingMongoRepository;
import org.springframework.stereotype.Component;

@Component
public class SaveRatingAdapter implements SavePort<Rating> {
    private final RatingMongoRepository ratingMongoRepository;
    private final RatingInfraMapper mapper;

    public SaveRatingAdapter(RatingMongoRepository ratingMongoRepository, RatingInfraMapper mapper) {
        this.ratingMongoRepository = ratingMongoRepository;
        this.mapper = mapper;
    }

    @Override
    public Rating save(Rating item) {
        RatingEntity entity = mapper.toEntity(item);
        RatingEntity savedEntity = ratingMongoRepository.save(entity);

        return mapper.toModel(savedEntity);
    }
}
