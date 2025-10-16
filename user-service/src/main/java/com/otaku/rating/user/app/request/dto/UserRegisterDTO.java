package com.otaku.rating.user.app.request.dto;

import com.otaku.rating.user.core.model.UserRegister;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class UserRegisterDTO {
    private String userName;
    private String name;
    private String email;
    private String password;

    public UserRegister convertToEntity() {
        return new UserRegister(userName, name, email, password);
    }
}
