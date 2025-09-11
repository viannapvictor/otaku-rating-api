package com.otaku.rating.core.user.service.messaging;

import com.otaku.rating.core.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!prod")
@Slf4j
public class LogEmailSender implements EmailSender {
    @Override
    public void sendEmail(User user, String subject, String message) {
        log.info("[DEV] Email sent to: {}", user.getEmail().getValue());
        log.info("[DEV] Subject: {}", subject);
        log.info("[DEV] Body: {}", message);
        
        // Extract and decode confirmation code for DEV testing
        if (message.contains("code=")) {
            String[] parts = message.split("code=");
            if (parts.length > 1) {
                String encodedCode = parts[1].split("&")[0].split("\n")[0];
                try {
                    String decodedCode = java.net.URLDecoder.decode(encodedCode, "UTF-8");
                    log.info("[DEV] RAW CONFIRMATION CODE: {}", decodedCode);
                } catch (Exception e) {
                    log.warn("[DEV] Could not decode confirmation code: {}", encodedCode);
                }
            }
        }
    }
}
