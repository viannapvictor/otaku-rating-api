package com.otaku.rating.infra.user.entities;

import com.otaku.rating.core.user.model.supportobjects.Email;
import com.otaku.rating.core.user.model.supportobjects.EnumUserRole;
import com.otaku.rating.core.user.model.supportobjects.Name;
import com.otaku.rating.core.user.model.supportobjects.UserName;
import jakarta.persistence.*;
import lombok.*;

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
}
