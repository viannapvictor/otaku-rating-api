package com.otaku.rating.core.user.service;

import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.properties.Password;

public interface PasswordEnconderService {
    public boolean checkPassword(User user, Password givenPassword);
    String encryptPassword(Password password);
}
