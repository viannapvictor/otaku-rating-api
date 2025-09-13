package com.otaku.rating.core.user.model;

import com.otaku.rating.core.user.model.valueobjects.Name;
import com.otaku.rating.core.user.model.valueobjects.UserName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class UserUpdateInfoRequest {
    private Name name;
    private UserName userName;
} 