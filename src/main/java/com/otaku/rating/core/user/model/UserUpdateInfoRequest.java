package com.otaku.rating.core.user.model;

import com.otaku.rating.core.user.model.valueobject.Name;
import com.otaku.rating.core.user.model.valueobject.UserName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdateInfoRequest {
    private Name name;
    private UserName userName;
} 