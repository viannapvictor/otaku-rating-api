package com.otaku.rating.infra.anime.persistence;

import com.otaku.rating.core.anime.model.CreditRole;
import com.otaku.rating.infra.person.persistence.PersonEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "AnimeContribution")
@Table(name = "TB_ANIME_CONTRIBUTION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimeContributionEntity {
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;

    @ManyToOne
    @JoinColumn(name = "anime_id", nullable = false)
    private AnimeEntity anime;

    @Column(name = "role", nullable = false)
    private CreditRole role;
}
