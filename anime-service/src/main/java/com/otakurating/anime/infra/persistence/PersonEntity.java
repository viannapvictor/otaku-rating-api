package com.otakurating.anime.infra.persistence;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Document(collection = "persons")
@Getter
@Setter
@NoArgsConstructor
@Builder
public class PersonEntity {
    @Id
    private UUID id;

    @Field(name = "name")
    private String name;

    @Field(name = "description")
    private String description;

    @PersistenceCreator
    public PersonEntity(UUID id, String name, String description) {
        this.id = id == null ? UUID.randomUUID() : id;
        this.name = name;
        this.description = description;
    }
}
