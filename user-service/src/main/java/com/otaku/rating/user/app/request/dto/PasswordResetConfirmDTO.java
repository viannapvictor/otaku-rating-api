package com.otaku.rating.user.app.request.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PasswordResetConfirmDTO {
    private String code;
    private String newPassword;
}
