package com.otakurating.rating.app.response.mapper;

import com.otakurating.rating.app.response.dto.ReviewViewDTO;
import com.otakurating.rating.core.model.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewViewMapper {
    public ReviewViewDTO toEntity(Review model) {
        return new ReviewViewDTO(
                model.getRatingId(),
                model.getUserId(),
                model.getRate().getValue(),
                model.getComment().getValue()
        );
    }
}
