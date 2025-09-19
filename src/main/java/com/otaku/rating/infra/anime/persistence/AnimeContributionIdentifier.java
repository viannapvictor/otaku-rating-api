package com.otaku.rating.infra.anime.persistence;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class AnimeContributionIdentifier {
    private UUID personId;
    private String animeId;
}
