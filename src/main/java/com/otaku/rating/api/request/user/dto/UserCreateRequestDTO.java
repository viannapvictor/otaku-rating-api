package com.otaku.rating.api.request.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestDTO {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}