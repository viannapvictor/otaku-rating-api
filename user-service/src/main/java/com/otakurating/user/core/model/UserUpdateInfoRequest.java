package com.otakurating.user.core.model;

import com.otakurating.user.core.model.valueobject.Name;
import com.otakurating.user.core.model.valueobject.UserName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdateInfoRequest {
    private Name name;
    private UserName userName;
} 