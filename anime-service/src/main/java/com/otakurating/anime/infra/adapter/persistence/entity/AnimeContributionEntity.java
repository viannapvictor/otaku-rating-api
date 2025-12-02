package com.otakurating.anime.infra.adapter.persistence.entity;

import com.otakurating.anime.core.model.CreditRole;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "anime_contributions")
public record AnimeContributionEntity(
    @Id
    AnimeContributionIdentifier id,

    @Field(name = "role")
    CreditRole role
) {
}
