package com.otaku.rating.infra.user.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity(name = "EmailConfirmation")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "email_confirmations")
public class EmailConfirmationEntity {
    @Id
    private String code;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_email_confirmation"), nullable = false, unique = true)
    private UserEntity user;

    @Column(name = "new_email", nullable = true)
    private String newEmail;

    @Column(name = "expiration", nullable = false)
    private Instant expiration;
}
