package com.otakurating.rating.app.response.dto;

public record RatingViewDTO(String id, long totalRate, int reviewsCount, Float averageRate) {
}
