package com.otaku.rating.user.app.request.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailConfirmationRequestDTO {
    private String email;
    private String code;
}
