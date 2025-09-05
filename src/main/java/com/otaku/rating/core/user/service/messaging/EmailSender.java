package com.otaku.rating.core.user.service.messaging;

import com.otaku.rating.core.user.model.User;

public interface EmailSender {
    void sendEmail(User user, String subject, String message);
}
