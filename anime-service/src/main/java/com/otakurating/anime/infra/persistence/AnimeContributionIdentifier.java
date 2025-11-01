package com.otakurating.anime.infra.persistence;

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
