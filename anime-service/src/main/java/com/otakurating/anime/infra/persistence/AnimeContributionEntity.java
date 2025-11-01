package com.otakurating.anime.infra.persistence;

import com.otakurating.anime.core.model.CreditRole;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "anime_contributions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimeContributionEntity {
    @Id
    private AnimeContributionIdentifier id;

    @Field(name = "role")
    private CreditRole role;
}
