package com.otakurating.rating.infra.adapter.persistence.adapter;

import com.otakurating.rating.core.model.Rating;
import com.otakurating.rating.core.port.out.FindRatingPort;
import com.otakurating.rating.infra.adapter.persistence.mapper.RatingInfraMapper;
import com.otakurating.rating.infra.adapter.persistence.repository.RatingMongoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindRatingAdapter implements FindRatingPort {
    private final RatingMongoRepository ratingMongoRepository;
    private final RatingInfraMapper mapper;

    public FindRatingAdapter(RatingMongoRepository ratingMongoRepository, RatingInfraMapper mapper) {
        this.ratingMongoRepository = ratingMongoRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Rating> findById(String id) {
        return ratingMongoRepository.findById(id)
                .map(mapper::toModel);
    }

    @Override
    public boolean existsById(String id) {
        return ratingMongoRepository.existsById(id);
    }
}
