package com.otaku.rating.infra.person.persistence;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;


@Document(collection = "persons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonEntity {
    @Id
    private UUID id;

    @Field(name = "name")
    private String name;

    @Field(name = "description")
    private String description;
}
