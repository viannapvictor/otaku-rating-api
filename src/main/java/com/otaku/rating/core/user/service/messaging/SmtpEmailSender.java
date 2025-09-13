package com.otaku.rating.core.user.service.messaging;

import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.properties.email.EmailProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
@RequiredArgsConstructor
public class SmtpEmailSender implements EmailSender {
    private final JavaMailSender mailSender;
    private final EmailProperties emailProperties;

    @Override
    public void sendEmail(User user, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailProperties.getUsername());
        mailMessage.setTo(user.getEmail().getValue());
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }
}
