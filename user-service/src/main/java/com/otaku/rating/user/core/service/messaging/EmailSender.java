package com.otaku.rating.user.core.service.messaging;

import com.otaku.rating.user.core.model.User;

public interface EmailSender {
    void sendEmail(User user, String subject, String message);
}
