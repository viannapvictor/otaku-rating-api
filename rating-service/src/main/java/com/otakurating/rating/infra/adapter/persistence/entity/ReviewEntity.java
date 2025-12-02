package com.otakurating.rating.infra.adapter.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "reviews")
public record ReviewEntity(
    @Id
    ReviewEntityIdentifier id,

    @Field(name = "rate")
    int rate,

    @Field(name = "comment")
    String comment
) {
}
