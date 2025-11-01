package com.otakurating.user.app.request.dto;

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