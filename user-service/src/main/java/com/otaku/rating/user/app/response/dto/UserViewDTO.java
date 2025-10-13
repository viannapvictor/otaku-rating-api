package com.otaku.rating.user.app.response.dto;

import com.otaku.rating.user.core.model.valueobject.EnumUserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserViewDTO {
    private Long id;
    private String userName;
    private String name;
    private String email;
    private EnumUserRole role;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}