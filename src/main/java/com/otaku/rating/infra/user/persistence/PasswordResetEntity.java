package com.otaku.rating.infra.user.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity(name = "PasswordReset")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "password_resets")
public class PasswordResetEntity {
    @Id
    private String code;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_password_reset"))
    private UserEntity user;

    @Column(nullable = false)
    private Instant expiration;
}
