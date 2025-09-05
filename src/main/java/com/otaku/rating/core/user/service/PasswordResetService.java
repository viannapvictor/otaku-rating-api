package com.otaku.rating.core.user.service;

import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.user.model.PasswordReset;
import com.otaku.rating.core.user.model.User;

public interface PasswordResetService {
    PasswordReset createPasswordResetRequest(User user) throws ValidationException;
    PasswordReset resetPassword(String code) throws ValidationException;
    void deleteFromUser(User user);
}