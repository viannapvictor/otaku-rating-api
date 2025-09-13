package com.otaku.rating.core.user.service;

import com.otaku.rating.core.user.model.PasswordReset;
import com.otaku.rating.core.user.model.User;

public interface PasswordResetService {
    PasswordReset createPasswordResetRequest(User user);
    PasswordReset resetPassword(String code);
    void deleteFromUser(User user);
}