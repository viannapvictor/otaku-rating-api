package com.otaku.rating.infra.user.entities;

import com.otaku.rating.core.user.model.valueobjects.Email;
import com.otaku.rating.core.user.model.valueobjects.EnumUserRole;
import com.otaku.rating.core.user.model.valueobjects.Name;
import com.otaku.rating.core.user.model.valueobjects.UserName;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "User")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(
    name = "users",
    indexes = {
            @Index(columnList = "user_name", unique = true),
            @Index(columnList = "email", unique = true)
    }
)
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_name", nullable = false, unique = true, length = UserName.MAX_LENGTH)
    private String userName;
    
    @Column(name = "name", nullable = false, length = Name.MAX_LENGTH)
    private String name;
    
    @Column(name = "email", unique = true, nullable = false, length = Email.MAX_LENGTH)
    private String email;
    
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private EnumUserRole role;
    
    @Column(name = "active", nullable = false)
    private boolean active;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<RefreshTokenEntity> refreshTokens;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private EmailConfirmationEntity emailConfirmation;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private PasswordResetEntity passwordReset;
}
