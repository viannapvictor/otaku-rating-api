package com.otaku.rating.api.request.user.dto;

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
