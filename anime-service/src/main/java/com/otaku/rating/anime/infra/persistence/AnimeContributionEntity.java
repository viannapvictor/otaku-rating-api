package com.otaku.rating.anime.infra.persistence;

import com.otaku.rating.anime.core.model.CreditRole;
import jakarta.persistence.Id;
import lombok.*;
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
