package com.otaku.rating.core.user.service;

import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.valueobject.Password;

public interface PasswordEncoderService {
    String encryptPassword(Password password);
    boolean checkPassword(User user, Password givenPassword);
}
