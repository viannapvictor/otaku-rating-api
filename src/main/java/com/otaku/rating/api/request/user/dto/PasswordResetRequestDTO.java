package com.otaku.rating.api.request.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PasswordResetRequestDTO {
    private String email;
}
