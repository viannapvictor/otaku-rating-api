package com.otaku.rating.user.app.request.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PasswordResetRequestDTO {
    private String email;
}
