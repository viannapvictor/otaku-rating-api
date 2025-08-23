package com.otaku.rating.infra.anime.persistence;

import com.otaku.rating.core.anime.model.AnimeDescription;
import com.otaku.rating.core.anime.model.AnimeTitle;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "Anime")
@Table(name = "TB_ANIME")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimeEntity {
    @Id
    private String id;

    @Column(name = "title", nullable = false, length = AnimeTitle.MAX_LENGTH)
    private String title;

    @Column(name = "description", nullable = false, length = AnimeDescription.MAX_LENGTH)
    private String description;

    @Column(name = "release", nullable = false)
    private LocalDate release;

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL)
    private List<AnimeContributionEntity> contributions;
}
