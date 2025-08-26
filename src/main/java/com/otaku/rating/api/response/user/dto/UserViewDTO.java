package com.otaku.rating.api.response.user.dto;

import com.otaku.rating.core.user.model.supportobjects.EnumUserRole;
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