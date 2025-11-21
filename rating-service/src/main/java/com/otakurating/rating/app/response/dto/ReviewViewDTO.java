package com.otakurating.rating.app.response.dto;

public record ReviewViewDTO(String ratingId, String userId, int rate, String comment) {
}
