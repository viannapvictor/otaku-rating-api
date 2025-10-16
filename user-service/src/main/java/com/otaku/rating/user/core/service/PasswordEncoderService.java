package com.otaku.rating.user.core.service;

import com.otaku.rating.user.core.model.User;
import com.otaku.rating.user.core.model.valueobject.Password;

public interface PasswordEncoderService {
    String encryptPassword(Password password);
    boolean checkPassword(User user, Password givenPassword);
}
