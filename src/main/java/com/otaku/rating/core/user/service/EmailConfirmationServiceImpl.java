package com.otaku.rating.core.user.service;

import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.user.model.EmailConfirmation;
import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.properties.user.UserProperties;
import com.otaku.rating.core.user.model.valueobjects.Email;
import com.otaku.rating.core.user.repository.EmailConfirmationRepository;
import com.otaku.rating.core.user.service.messaging.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@Service
@RequiredArgsConstructor
public class EmailConfirmationServiceImpl implements EmailConfirmationService {
    private static final ResourceBundle messages = ResourceBundle.getBundle("messages");

    private final EmailConfirmationRepository emailConfirmationRepository;
    private final UserProperties userProperties;
    private final EmailSender emailSender;

    @Override
    @Transactional
    public EmailConfirmation confirmEmail(String code, Email currentEmail) throws ValidationException {
        if (currentEmail == null) {
            throw new IllegalArgumentException("The current email must not be empty.");
        }
        EmailConfirmation emailConfirmation = emailConfirmationRepository.findByUserEmail(currentEmail)
                .orElseThrow(() -> new ValidationException("EMAIL_ALREADY_CONFIRMED", "Email already confirmed"));
        if (emailConfirmation.isExpired() || !emailConfirmation.getCode().matches(code)) {
            throw new ValidationException("EMAIL_CONFIRMATION_CODE_INVALID", "Email confirmation code is invalid");
        }
        emailConfirmationRepository.delete(emailConfirmation);
        return emailConfirmation;
    }

    @Override
    public boolean existsByEmail(Email email) {
        return emailConfirmationRepository.existsByUserEmail(email);
    }

    @Override
    @Transactional
    public EmailConfirmation addEmailConfirmation(User user, Email newEmail) throws ValidationException {
        Optional<EmailConfirmation> active = emailConfirmationRepository.findByUserEmail(user.getEmail());
        if (active.isPresent()) {
            if (!active.get().isExpired()) throw new ValidationException("PENDING_EMAIL_CONFIRMATION", "Pending email confirmation");
            emailConfirmationRepository.delete(active.get());
        }
        EmailConfirmation emailConfirmation = new EmailConfirmation(newEmail, user, userProperties);
        EmailConfirmation savedEmailConfirmation = emailConfirmationRepository.save(emailConfirmation);

        String subject = messages.getString("message.email.confirmation.subject");
        String bodyKey = savedEmailConfirmation.getNewEmail() == null
                ? "message.email.confirmation.new.account.body"
                : "message.email.confirmation.existing.account.body";
        String body = String.format(
                messages.getString(bodyKey),
                user.getName().getValue(),
                user.getEmail().getValue(),
                savedEmailConfirmation.getCode().encodeToUrlCode(),
                userProperties.getEmailConfirmationExpirationMinutes()
        );
        emailSender.sendEmail(user, subject, body);
        return savedEmailConfirmation;
    }

    @Override
    public List<EmailConfirmation> findExpiredAccounts() {
        Instant now = Instant.now();
        return emailConfirmationRepository.findAllExpired(now);
    }

    @Override
    public void delete(EmailConfirmation emailConfirmation) {
        emailConfirmationRepository.delete(emailConfirmation);
    }

    @Override
    public void deleteFromUser(User user) {
        emailConfirmationRepository.deleteFromUser(user);
    }
}
