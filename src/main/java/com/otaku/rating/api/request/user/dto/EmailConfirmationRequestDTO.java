package com.otaku.rating.api.request.user.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailConfirmationRequestDTO {
    private String email;
    private String code;
}
