package com.otaku.rating.infra.user.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "RefreshToken")
@Table(name = "refresh_tokens")
public class RefreshTokenEntity {
    @Id
    private String code;

    @Column(name = "expiration", nullable = false)
    private Instant expiration;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_refresh_token"))
    private UserEntity user;
}
