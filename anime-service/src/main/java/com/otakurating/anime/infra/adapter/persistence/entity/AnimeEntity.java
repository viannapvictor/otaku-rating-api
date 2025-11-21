package com.otakurating.anime.infra.adapter.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Document(collection = "animes")
public record AnimeEntity(
    @Id
    String id,

    @Field(name = "title")
    String title,

    @Field(name = "description")
    String description,

    @Field(name = "release")
    LocalDate release
) {
}
