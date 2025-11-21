package com.otakurating.anime.infra.adapter.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Document(collection = "persons")
public record PersonEntity(
    @Id
    UUID id,

    @Field(name = "name")
    String name,

    @Field(name = "description")
    String description
) {
}
