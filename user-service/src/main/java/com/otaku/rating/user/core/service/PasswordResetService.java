package com.otaku.rating.user.core.service;

import com.otaku.rating.user.core.model.PasswordReset;
import com.otaku.rating.user.core.model.User;

public interface PasswordResetService {
    PasswordReset createPasswordResetRequest(User user);
    PasswordReset resetPassword(String code);
    void deleteFromUser(User user);
}