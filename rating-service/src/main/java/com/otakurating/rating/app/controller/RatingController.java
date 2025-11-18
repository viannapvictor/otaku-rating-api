package com.otakurating.rating.app.controller;

import com.otakurating.rating.app.request.dto.ReviewCreateDTO;
import com.otakurating.rating.app.response.mapper.ReviewViewMapper;
import com.otakurating.rating.app.response.dto.ApiResponse;
import com.otakurating.rating.app.response.dto.RatingViewDTO;
import com.otakurating.rating.app.response.dto.ReviewViewDTO;
import com.otakurating.rating.app.response.mapper.RatingViewMapper;
import com.otakurating.rating.core.command.CreateReviewCommand;
import com.otakurating.rating.core.command.GetRatingByIdCommand;
import com.otakurating.rating.core.command.GetUserRatingReviewCommand;
import com.otakurating.rating.core.model.Rating;
import com.otakurating.rating.core.model.Review;
import com.otakurating.rating.core.port.in.CreateReviewUseCase;
import com.otakurating.rating.core.port.in.GetRatingByIdUseCase;
import com.otakurating.rating.core.port.in.GetUserRatingReviewUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/rating")
public class RatingController {
    private final GetRatingByIdUseCase getRatingByIdUseCase;
    private final GetUserRatingReviewUseCase getUserRatingReviewUseCase;
    private final CreateReviewUseCase createReviewUseCase;
    private final RatingViewMapper ratingViewMapper;
    private final ReviewViewMapper reviewViewMapper;

    public RatingController(GetRatingByIdUseCase getRatingByIdUseCase, GetUserRatingReviewUseCase getUserRatingReviewUseCase, CreateReviewUseCase createReviewUseCase, RatingViewMapper ratingViewMapper, ReviewViewMapper reviewViewMapper) {
        this.getRatingByIdUseCase = getRatingByIdUseCase;
        this.getUserRatingReviewUseCase = getUserRatingReviewUseCase;
        this.createReviewUseCase = createReviewUseCase;
        this.ratingViewMapper = ratingViewMapper;
        this.reviewViewMapper = reviewViewMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RatingViewDTO>> getRatingById(@PathVariable("id") String id) {
        GetRatingByIdCommand command = new GetRatingByIdCommand(id);
        Rating rating = getRatingByIdUseCase.getById(command);
        RatingViewDTO view = ratingViewMapper.toEntity(rating);

        return ApiResponse.success(view).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}/review")
    public ResponseEntity<ApiResponse<ReviewViewDTO>> getUserReview(
            @PathVariable("id") String id,
            Authentication authentication
    ) {
        GetUserRatingReviewCommand command = new GetUserRatingReviewCommand(id, (String)authentication.getPrincipal());
        Review review = getUserRatingReviewUseCase.getById(command);
        ReviewViewDTO view = reviewViewMapper.toEntity(review);

        return ApiResponse.success(view).createResponse(HttpStatus.OK);
    }

    @PostMapping("/{id}/review")
    public ResponseEntity<ApiResponse<ReviewViewDTO>> createReview(
            @PathVariable("id") String id,
            @RequestBody ReviewCreateDTO form,
            Authentication authentication
    ) {
        CreateReviewCommand command = new CreateReviewCommand(
                id,
                (String)authentication.getPrincipal(),
                form.rate(),
                form.comment()
        );
        Review review = createReviewUseCase.create(command);
        ReviewViewDTO view = reviewViewMapper.toEntity(review);

        return ApiResponse.success(view).createResponse(HttpStatus.CREATED);
    }
}
