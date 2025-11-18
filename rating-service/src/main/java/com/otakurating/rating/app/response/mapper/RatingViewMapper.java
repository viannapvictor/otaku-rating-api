package com.otakurating.rating.app.response.mapper;

import com.otakurating.rating.app.response.dto.RatingViewDTO;
import com.otakurating.rating.core.model.Rating;
import org.springframework.stereotype.Component;

@Component
public class RatingViewMapper {
    public RatingViewDTO toEntity(Rating model) {
        return new RatingViewDTO(
                model.getId(),
                model.getTotalRate(),
                model.getReviewsCount(),
                model.getAverageRate()
        );
    }
}
