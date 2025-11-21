package com.otakurating.rating.infra.adapter.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "ratings")
public record RatingEntity(
    @Id
    String id,

    @Field(name = "totalRate")
    long totalRate,

    @Field(name = "reviewsCount")
    int reviewsCount
) {
}
