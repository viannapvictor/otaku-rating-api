package com.otakurating.rating.infra.adapter.persistence.entity;

public record ReviewEntityIdentifier(
    String ratingId,
    String userId
) {
}
