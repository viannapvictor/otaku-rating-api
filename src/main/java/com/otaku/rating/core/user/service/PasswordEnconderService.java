package com.otaku.rating.core.user.service;

import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.supportobjects.Password;

public interface PasswordEnconderService {
    String encryptPassword(Password password);
}
