package com.otaku.rating.user.core.service;

import com.otaku.rating.user.core.model.EmailConfirmation;
import com.otaku.rating.user.core.model.User;
import com.otaku.rating.user.core.model.valueobject.Email;

import java.util.List;

public interface EmailConfirmationService {
    EmailConfirmation confirmEmail(String code, Email currentEmail);
    boolean existsByEmail(Email email);
    EmailConfirmation addEmailConfirmation(User user, Email newEmail);
    List<EmailConfirmation> findExpiredAccounts();
    void delete(EmailConfirmation emailConfirmation);
    void deleteFromUser(User user);
}