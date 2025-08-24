package com.otaku.rating.infra.person.persistence;

import com.otaku.rating.infra.anime.persistence.AnimeContributionEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Person")
@Table(name = "TB_PERSON")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<AnimeContributionEntity> contributions;
}
