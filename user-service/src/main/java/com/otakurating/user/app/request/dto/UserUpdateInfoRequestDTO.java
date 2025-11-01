package com.otakurating.user.app.request.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateInfoRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
} 